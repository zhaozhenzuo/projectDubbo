package com.test.cartservice.store;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;

import com.test.framework.base.DataStoreInf;
import com.test.framework.domain.ResponseResult;
import com.test.framework.redis.RedisCodeInfo;
import com.test.framework.redis.RedisUtil;
import com.test.framework.util.CodeInfoClass;
import com.test.framework.util.ResponseUtil;

/**
 * redis作为底层存储实现 <br/>
 * 
 * 
 * @author hzzhaozhenzuo
 * 
 */
@Component
public class RedisDataStore implements DataStoreInf<Integer> {

	@Autowired
	private RedisUtil redisUtil;

	private static final Logger logger = LoggerFactory.getLogger(RedisDataStore.class);

	@Override
	public ResponseResult<Integer> putToHashNotExist(String key, String field, Integer value) {
		Jedis jedis = null;
		try {
			jedis = redisUtil.getConnection();
			Long res = jedis.hsetnx(key, field, String.valueOf(value));
			if (res == null || res != 1) {
				logger.error("key,field is already exists or fail,key:" + key + ",field:" + field + ",res:" + res);
				return ResponseUtil.generateResponse(CodeInfoClass.COMMON_FAIL);
			}

			return ResponseUtil.generateResponse(CodeInfoClass.SUCCESS);

		} catch (Exception e) {
			logger.error("redis fail", e);
			return ResponseUtil.generateResponse(CodeInfoClass.COMMON_FAIL);
		} finally {
			redisUtil.releaseConnection(jedis);
		}
	}

	@Override
	public ResponseResult<Integer> deleteHashByKeyWhenExist(String key) {
		throw new UnsupportedOperationException("not support deleteHashByKeyWhenExist");
	}

	@Override
	public ResponseResult<Integer> deleteValueOfHashByKeyAndFieldWhenExist(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = redisUtil.getConnection();
			Long res = jedis.hdel(key, field);
			if (res == null || res != 1) {
				return ResponseUtil.generateResponse(CodeInfoClass.COMMON_FAIL);
			}
			return ResponseUtil.generateResponse(CodeInfoClass.SUCCESS);
		} catch (Exception e) {
			logger.error("redis fail", e);
			return ResponseUtil.generateResponse(CodeInfoClass.COMMON_FAIL);
		} finally {
			redisUtil.releaseConnection(jedis);
		}
	}

	@Override
	public Map<String, Integer> getAllFieldAndValueFromHash(String key) {
		Jedis jedis = null;
		try {
			jedis = redisUtil.getConnection();
			Map<String, Integer> resMap = new HashMap<String, Integer>();
			Map<String, String> resTempMap = jedis.hgetAll(key);
			if (resTempMap == null || resTempMap.isEmpty()) {
				return resMap;
			}

			for (Entry<String, String> entry : resTempMap.entrySet()) {
				resMap.put(entry.getKey(), Integer.valueOf(entry.getValue()));
			}

			return resMap;
		} catch (Exception e) {
			logger.error("redis fail", e);
			return null;
		} finally {
			redisUtil.releaseConnection(jedis);
		}
	}

	@Override
	public ResponseResult<Integer> getValueByKeyAndField(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = redisUtil.getConnection();
			String resFromRedis = jedis.hget(key, field);
			if (resFromRedis==null || RedisCodeInfo.NIL.equalsIgnoreCase(resFromRedis)) {
				return ResponseUtil.generateResponse(CodeInfoClass.NOT_EXIST, null);
			}

			return ResponseUtil.generateResponse(CodeInfoClass.SUCCESS, Integer.valueOf(resFromRedis));
		} catch (Exception e) {
			logger.error("redis fail", e);
			return ResponseUtil.generateResponse(CodeInfoClass.COMMON_FAIL, null, "redis fail");
		} finally {
			redisUtil.releaseConnection(jedis);
		}

	}

	@Override
	public ResponseResult<Integer> increaseValueByNum(String key, String field, Integer num) {
		if (num == null || num <= 0) {
			logger.error("num must be set and larger than 0 when increaseValueByNum");
			return ResponseUtil.generateResponse(CodeInfoClass.COMMON_FAIL);
		}

		Jedis jedis = null;
		try {
			jedis = redisUtil.getConnection();
			int resInt = jedis.hincrBy(key, field, Long.valueOf(num)).intValue();
			return ResponseUtil.generateResponse(CodeInfoClass.SUCCESS, resInt);
		} catch (Exception e) {
			logger.error("redis fail", e);
			return ResponseUtil.generateResponse(CodeInfoClass.COMMON_FAIL);
		} finally {
			redisUtil.releaseConnection(jedis);
		}
	}

	@Override
	public ResponseResult<Integer> decreaseValueByNum(String key, String field, Integer num) {
		if (num == null || num <= 0) {
			logger.error("num must be set and larger than 0 when decreaseValueByNum");
			return ResponseUtil.generateError();
		}

		Jedis jedis = null;
		try {
			jedis = redisUtil.getConnection();
			int resInt = jedis.hincrBy(key, field, num * -1).intValue();
			return ResponseUtil.generateSuccess(resInt);
		} catch (Exception e) {
			logger.error("redis fail", e);
			return ResponseUtil.generateError();
		} finally {
			redisUtil.releaseConnection(jedis);
		}

	}

}

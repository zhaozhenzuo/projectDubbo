//package com.test.cartservice.store;
//
//import java.util.HashMap;
//import java.util.Map;
//
//import org.jboss.netty.util.internal.ConcurrentHashMap;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.test.framework.base.DataStoreInf;
//import com.test.framework.domain.ResponseResult;
//import com.test.framework.util.CodeInfoClass;
//import com.test.framework.util.ResponseUtil;
//
///**
// * 只处只用concurrentHashMap作为存储只是作为示例 <br/>
// * 
// * 
// * @author hzzhaozhenzuo
// * 
// */
//// @Component
//public class LocalMapDataStore implements DataStoreInf<Integer> {
//
//	private static final Map<String, Map<String, Integer>> dataMap = new ConcurrentHashMap<String, Map<String, Integer>>();
//
//	private static final Logger logger = LoggerFactory.getLogger(LocalMapDataStore.class);
//
//	@Override
//	public synchronized boolean putToHashNotExist(String key, String field, Integer value) {
//		Map<String, Integer> valueMap = dataMap.get(key);
//		if (valueMap == null) {
//			valueMap = new HashMap<String, Integer>();
//		}
//		valueMap.put(field, value);
//		dataMap.put(key, valueMap);
//		return true;
//	}
//
//	@Override
//	public synchronized boolean deleteHashByKeyWhenExist(String key) {
//		Map<String, Integer> resMap = dataMap.remove(key);
//		return resMap != null ? true : false;
//	}
//
//	@Override
//	public synchronized boolean deleteValueOfHashByKeyAndFieldWhenExist(String key, String field) {
//		Map<String, Integer> valueMap = dataMap.get(key);
//		if (valueMap == null) {
//			logger.error("no valueMap found in key:" + key);
//			return false;
//		}
//
//		Integer resInteger = valueMap.remove(field);
//		boolean flag = resInteger != null;
//		if (flag) {
//			dataMap.put(key, valueMap);
//		}
//		return flag;
//	}
//
//	@Override
//	public Map<String, Integer> getAllFieldAndValueFromHash(String key) {
//		return dataMap.get(key);
//	}
//
//	@Override
//	public ResponseResult<Integer> getValueByKeyAndField(String key, String field) {
//		Map<String, Integer> valueMap = dataMap.get(key);
//		if (valueMap == null) {
//			logger.error("no valueMap found in key:" + key);
//			return ResponseUtil.generateResponse(CodeInfoClass.NOT_EXIST_CODE, null);
//		}
//		Integer resInt = valueMap.get(field);
//		return ResponseUtil.generateResponse(CodeInfoClass.SUCCESS_CODE, resInt);
//	}
//
//	@Override
//	public synchronized Integer increaseValueByNum(String key, String field, Integer num) {
//		if (num == null || num <= 0) {
//			logger.error("num must be set and larger than 0 when increaseValueByNum");
//			return CodeInfoClass.failInt;
//		}
//
//		Map<String, Integer> valueMap = dataMap.get(key);
//		if (valueMap == null) {
//			logger.error("no valueMap found in key:" + key);
//			return CodeInfoClass.failInt;
//		}
//		Integer oldValue = valueMap.get(field);
//		Integer valueUpdated = oldValue + num;
//		Integer res = valueMap.put(field, valueUpdated);
//		if (res == null) {
//			return CodeInfoClass.failInt;
//		}
//		return valueUpdated;
//	}
//
//	@Override
//	public synchronized Integer decreaseValueByNum(String key, String field, Integer num) {
//		if (num == null || num <= 0) {
//			logger.error("num must be set and larger than 0 when decreaseValueByNum");
//			return CodeInfoClass.failInt;
//		}
//
//		Map<String, Integer> valueMap = dataMap.get(key);
//		if (valueMap == null) {
//			logger.error("no valueMap found in key:" + key);
//			return CodeInfoClass.failInt;
//		}
//		Integer oldValue = valueMap.get(field);
//		if (oldValue == null) {
//			logger.error("no value for key:" + key + ",filed:" + field);
//			return CodeInfoClass.failInt;
//		}
//		Integer valueUpdated = oldValue - num;
//		Integer res = valueMap.put(field, valueUpdated);
//		if (res == null) {
//			return CodeInfoClass.failInt;
//		}
//		return valueUpdated;
//	}
//
//	@Override
//	public boolean deleteValueOfHashByKeyAndField(String key, String field) {
//		// TODO Auto-generated method stub
//		return false;
//	}
//
//}

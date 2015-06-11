package com.test.framework.redis;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * redis工具类
 * 
 * @author hzzhaozhenzuo
 * 
 */
@Component
public class RedisUtil implements InitializingBean {

	@Autowired
	private Environment env;

	// @Value("${redis.pool.maxTotal}")
	// private int maxTotal;
	//
	// @Value("${redis.pool.maxIdle}")
	// private int maxIdle;
	//
	// @Value("${redis.pool.maxWaitMillis}")
	// private long maxWaitMillis;
	//
	// @Value("${redis.pool.testOnBorrow}")
	// private boolean testOnBorrow;
	//
	// @Value("${redis.pool.testOnReturn}")
	// private boolean testOnReturn;

	private JedisPool pool;

	@Override
	public void afterPropertiesSet() throws Exception {

		try {
			String host = env.getProperty("spring.redis.host");
			int port = env.getProperty("spring.redis.port", Integer.class);
			
			pool = new JedisPool(host, port);

			JedisPoolConfig config = new JedisPoolConfig();

			// 设置池配置项值
			// config.setMaxTotal(maxTotal);
			// config.setMaxIdle(maxIdle);
			// config.setMaxWaitMillis(maxWaitMillis);
			// config.setTestOnBorrow(testOnBorrow);
			// config.setTestOnReturn(testOnReturn);

			// 根据配置实例化jedis池
			pool = new JedisPool(config, host, port);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public Jedis getConnection() {
		return pool.getResource();
	}

	public void releaseConnection(Jedis jedis) {
		if (jedis == null) {
			return;
		}
		pool.returnResource(jedis);
	}

}

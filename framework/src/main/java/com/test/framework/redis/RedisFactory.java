//package com.test.framework.redis;
//
//import org.springframework.beans.factory.annotation.Value;
//
//import redis.clients.jedis.Jedis;
//
///**
// * RedisFactory是单例的<br/>
// * 
// * 创建redisFactory实例时，不会创建jedisInstance实例，只有当调用getJedisInstance才创建<br/>
// * 
// * jedisInstance是单例
// * 
// * @author hzzhaozhenzuo
// * 
// */
//@Deprecated
//public class RedisFactory {
//
//	private Jedis jedisInstance;
//
//	private volatile boolean hasCreated = false;
//
//	@Value("${redis.url}")
//	private String host;
//
//	public Jedis getJedisInstance() {
//		if (!hasCreated) {
//			this.createJedis();
//		}
//		return jedisInstance;
//	}
//
//	public synchronized void createJedis() {
//		if (jedisInstance == null) {
//			jedisInstance = new Jedis(host);
//		}
//	}
//
//}

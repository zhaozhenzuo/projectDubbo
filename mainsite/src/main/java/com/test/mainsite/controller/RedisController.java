//package com.test.mainsite.controller;
//
//import java.util.Collection;
//
//import javax.annotation.Resource;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.Cache;
//import org.springframework.cache.CacheManager;
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.ListOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.test.framework.domain.ResponseResult;
//import com.test.framework.util.ResponseUtil;
//import com.test.mainsite.CacheFacadeTest;
//
//@Controller
//@RequestMapping("/test/redis")
//public class RedisController {
//
//	@Resource(name = "redisTemplate")
//	private ListOperations<String, String> listOps;
//
//	@Autowired
//	private RedisTemplate redisTemplate;
//
//	@Autowired
//	private CacheManager cacheManager;
//
//	@Autowired
//	private CacheFacadeTest cacheFacadeTest;
//
//	@RequestMapping("set")
//	public void testRedis() {
//		String key = "myqueue";
//		listOps.leftPush(key, "hi");
//		listOps.leftPush(key, "2");
//		System.out.println(listOps.leftPop(key));
//
//		HashOperations<String, String, Integer> hashOperations = redisTemplate.opsForHash();
//		hashOperations.put("cartStore", "1001", 10);
//
//		System.out.println(hashOperations.get("cartStore", "1001"));
//
//	}
//	
//
//	@RequestMapping("get")
//	public void getRedis() {
//
//		byte[] key=redisTemplate.getKeySerializer().serialize(1234L);
//		byte[] valueArr=redisTemplate.getConnectionFactory().getConnection().get(key);
//		Object value=redisTemplate.getKeySerializer().deserialize(valueArr);
//
//		String res = cacheFacadeTest.getContent1(1234L);
//		System.out.println(res);
//
//		String res2 = cacheFacadeTest.getContent2(2L);
//		System.out.println(res2);
//	}
//
//	@RequestMapping("evict")
//	public void evictCacheByName(@RequestParam String name) {
//
//		Cache cache = cacheManager.getCache(name);
//
//		cache.clear();
//
//		RedisTemplate redisTemplateObj = (RedisTemplate) cache.getNativeCache();
//		System.out.println(redisTemplateObj);
//	}
//
//	@RequestMapping("getAllCache")
//	@ResponseBody
//	public ResponseResult<Collection<String>> getAllCache() {
//
//		Collection<String> cacheNames = cacheManager.getCacheNames();
//
//		return ResponseUtil.generateSuccess(cacheNames);
//	}
//
//}

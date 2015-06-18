package com.test.mainsite.test;

import java.util.Map;

import org.jboss.netty.util.internal.ConcurrentHashMap;

public class IpSeeker {
	
	public static final Map<String, String> ipMap=new ConcurrentHashMap<String, String>();
	
	public void put(String ip,String value){
		ipMap.put(ip, value);
	}

}

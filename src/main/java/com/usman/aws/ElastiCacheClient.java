package com.usman.aws;

import org.springframework.stereotype.Component;

import redis.clients.jedis.Jedis;




public class ElastiCacheClient {

	String url = "usman-test.adjlue.0001.apn2.cache.amazonaws.com";				 
	int port = 6379;
	String password = "";

	Jedis jedis;

	public ElastiCacheClient() {
		jedis = new Jedis(url, 6379);
		jedis.connect();
	}

	public String set(String key, String value) {
		return jedis.set(key, value);
	}

	public String get(String key) {
		return jedis.get(key);
	}

	public boolean test() {
		return jedis.isConnected();
	}

}

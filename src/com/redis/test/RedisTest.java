/*
 * Author: Sathiyan
 * Date: 21-May-2016
 * Comments: Jedis jar version 2.8.0 and Apache common pool 2.4
 * Version: 1.0
 * 
 */

package com.redis.test;

import redis.clients.jedis.Jedis;

import com.redis.manager.RedisManager;

public class RedisTest {

	public static void main(String[] args) {
		RedisManager.getInstance().connect();
		Jedis jedis = RedisManager.getInstance().getJedis();
		System.out.println("pin Redis---->"+jedis.ping());
		jedis.set("sampleKey", "sampleVal");
		System.out.println("get total keys----->"+jedis.keys("*"));
		String foobar = jedis.get("foo23456");
		System.out.println(foobar);
		RedisManager.getInstance().release();

	}

}


/**
 * 
 */
package com.redis.test;

/*
 * Author: Sathiyan
 * Date: 29-May-2016
 * Comments: Jedis jar version 2.8.0 and Apache common pool 2.4
 * Version: 1.0
 * 
 */
import java.util.HashSet;
import java.util.Set;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;
import redis.clients.jedis.exceptions.JedisConnectionException;


public class JedisSentinelPoolTest {

  public static void main(String args[]) throws InterruptedException {
	  
    String master = "mymaster";// sentinel master name configured in sentinel.conf file. 
    Set<String> sentinels = new HashSet<String>();// creating set and add all the sentinel ip's and port 
    sentinels.add("192.168.139.229:26379");// 
    sentinels.add("192.168.139.131:26380");// 
    JedisSentinelPool pool = new JedisSentinelPool(master, sentinels);// create JedisSentinelPool
 

    // after borrowed instance from pool info
    Jedis jedis = pool.getResource();
    System.out.println("Master host:" + jedis.getClient().getHost() + ", Master port: " +
        jedis.getClient().getPort());
    Thread.sleep(100);

    // internal pool in JedisSentinelPool is changed during fail over
    // and new internal pool rejects returning this object, so this object will be discarded
    jedis.close();

    // borrow new object connected to new master
    jedis = pool.getResource();
    try {     
      System.out.println("pin Redis---->"+jedis.ping());
		jedis.setnx("foo23456", "bar12345");
		System.out.println("get total keys----->"+jedis.keys("*"));
		String foobar = jedis.get("foo23456");
		System.out.println(foobar);
    } catch (JedisConnectionException e) {
      e.printStackTrace();
    } finally {
      jedis.close();
    }
    pool.close();
  }
}


package com.redis.manager;
 
/*
 * Author: Sathiyan
 * Date: 21-May-2016
 * Comments: Jedis jar version 2.8.0 and Apache common pool 2.4
 * Version: 1.0
 * 
 */
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
 
public class RedisManager {
    private static final RedisManager instance = new RedisManager();
    private static JedisPool pool;
    private RedisManager() {}
    public final static RedisManager getInstance() {
        return instance;
    }
    public void connect() {
       /** // Create and set a JedisPoolConfig
    	//GenericObjectPoolConfig new =GenericObjectPoolConfig();
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // Maximum active connections to Redis instance
        poolConfig.setMaxActive(20);
        // Tests whether connection is dead when connection
        // retrieval method is called
        poolConfig.setTestOnBorrow(true);
        /* Some extra configuration */
        // Tests whether connection is dead when returning a
        // connection to the pool
    	/**  poolConfig.setTestOnReturn(true);
        // Number of connections to Redis that just sit there
        // and do nothing
        poolConfig.setMaxIdle(5);
        // Minimum number of idle connections to Redis
        // These can be seen as always open and ready to serve
        poolConfig.setMinIdle(1);
        // Tests whether connections are dead during idle periods
        poolConfig.setTestWhileIdle(true);
        // Maximum number of connections to test in each idle check
        poolConfig.setNumTestsPerEvictionRun(10);
        // Idle connection checking period
        //poolConfig.setTimeBetweenEvictionRunsMillis(60000);
        // Create the jedisPool
//        pool = new JedisPool(poolConfig, "198.168.139.131", 6380);
//        System.out.println("poolConfig....."+poolConfig);
//        System.out.println("pool..."+pool);/
        **/
        String host = "";//192.168.139.229 //104.155.122.184
        int port = 6380;
        // version 2.1 starts 
        //JedisSentinelPool jedisPool = new JedisSentinelPool("mymaster", sentinels);
//        JedisPoolConfig config = new JedisPoolConfig();
//        config.maxActive = 1000;
//        config.maxIdle = 10;
//        config.minIdle = 1;
//        config.maxWait = 30000;
//        config.numTestsPerEvictionRun = 3;
//        config.testOnBorrow = true;
//        config.testOnReturn =  true;
//        config.testWhileIdle =  true;
//        config.timeBetweenEvictionRunsMillis = 30000;
//        pool = new JedisPool( config,host,port); 
     // version 2.1 end  
        
        JedisPoolConfig poolConfig=new JedisPoolConfig();
        poolConfig.setMaxWaitMillis(30000);      
        poolConfig.setMaxIdle(5);
        poolConfig.setMinIdle(1);
        poolConfig.setTestOnBorrow(true);
        poolConfig.setTestOnReturn(true);
        poolConfig.setTestWhileIdle(true);
        poolConfig.setNumTestsPerEvictionRun(10);
        poolConfig.setTimeBetweenEvictionRunsMillis(60000);
        pool=new JedisPool(poolConfig,host,port);
    }
    public void release() {
        pool.destroy();
    }
    public Jedis getJedis() {
        return pool.getResource();
    }
    @SuppressWarnings("deprecation")
	public void returnJedis(Jedis jedis) {
        pool.returnResource(jedis);   }
    
    
    
}
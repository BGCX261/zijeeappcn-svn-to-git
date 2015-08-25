package com.zhongs.mydemo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class TestJedis {
    static String ip = "192.168.1.173";
    static String ip2 = "192.168.1.172";

    static int port = 6379;

    public static void main(String[] args) {
        testPoll();
    }

    private static void testPoll() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxActive(100);
        config.setMaxIdle(20);
        config.setMaxWait(1000l);
        JedisPool pool = new JedisPool(config, ip2, port);
        // 使用时：
        Jedis jedis = pool.getResource();

//        jedis.set("foo", "ba222r");

        // Map map = new HashMap();
        // jedis.set("foo2", map);
        String value = jedis.get("foo");
        System.out.println(value);

        // 使用结束后要将jedis放回pool中：
        pool.returnResource(jedis);
    }

    private static void testShardPoll() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxActive(100);
        config.setMaxIdle(20);
        config.setMaxWait(1000l);

        JedisShardInfo jedisShardInfo = new JedisShardInfo(ip, port);
        JedisShardInfo jedisShardInfo2 = new JedisShardInfo(ip2, port);

        List<JedisShardInfo> list = new ArrayList<JedisShardInfo>();
        list.add(jedisShardInfo);
        list.add(jedisShardInfo2);
        ShardedJedisPool pool = new ShardedJedisPool(config, list);

        // 使用时：
        ShardedJedis jedis = pool.getResource();

        jedis.set("foo", "ba222r" + (new Date()));

        // Map map = new HashMap();
        // jedis.set("foo2", map);
        String value = jedis.get("foo");
        System.out.println(value);

        // 使用结束后要将jedis放回pool中：
        pool.returnResource(jedis);
//        关闭 Redis
        pool.destroy(); 
    }

    // <bean id="jedisPoolConfig" class="redis.clients.jedis.JedisPoolConfig">
    // <property name="maxActive" value="50" />
    // <property name="maxIdle" value="10" />
    // <property name="maxWait" value="1000" />
    // <property name="testOnBorrow" value="true"/>
    // </bean>
    //
    // <bean id="jedisShardInfo" class="redis.clients.jedis.JedisShardInfo">
    // <constructor-arg index="0" value="reids服务器ip" />
    // <constructor-arg index="1" value="redis服务器port" type="int"/>
    // </bean>
    //
    // <bean id="jedis" class="redis.clients.jedis.ShardedJedisPool"
    // factory-method="getResource">
    // <constructor-arg index="0" ref="jedisPoolConfig" />
    // <constructor-arg index="1">
    // <list>
    // <ref bean="jedisShardInfo" />
    // </list>
    // </constructor-arg>
    // </bean>

    private static void simple() {
        Jedis jedis = new Jedis(ip, port);
        jedis.set("foo", "bar");
        String value = jedis.get("foo");
        System.out.println(value);
    }

}

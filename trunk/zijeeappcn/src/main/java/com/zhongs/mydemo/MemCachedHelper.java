package com.zhongs.mydemo;

import java.util.Date;

import com.alisoft.xplatform.asf.cache.ICacheManager;
import com.alisoft.xplatform.asf.cache.IMemcachedCache;
import com.alisoft.xplatform.asf.cache.memcached.CacheUtil;
import com.alisoft.xplatform.asf.cache.memcached.MemcachedCacheManager;

public class MemCachedHelper {
    private static ICacheManager<IMemcachedCache> manager = null;
    private static IMemcachedCache cache;
    private static MemCachedHelper instance;

    private MemCachedHelper() {
        manager = CacheUtil.getCacheManager(IMemcachedCache.class, MemcachedCacheManager.class.getName());
        manager.start();
        cache = manager.getCache("mclient0");
    }

    /**
     * @return
     */
    public static MemCachedHelper getInstance() {
        if (instance == null) {
            synchronized (MemCachedHelper.class) {
                if (instance == null) {
                    instance = new MemCachedHelper();
                }
            }
        }
        return instance;
    }

    public void stop() {
        manager.stop();
    }

    /**
     * @param key
     * @param value
     * @return
     */
    public Object put(String key, Object value) {
        return cache.put(key, value);
    }

    /**
     * @param key
     * @param value
     * @param expiry
     * @return
     */
    public Object put(String key, Object value, Date expiry) {
        return cache.put(key, value, expiry);
    }

    /**
     * 
     * @param key
     * @return
     */
    public Object getData(String key) {
        return cache.get(key);
    }
    
    public static void main(String[] args) {
        MemCachedHelper mem =  MemCachedHelper.getInstance();
        
        System.out.println(mem.getData("dhgate_common_minimal_459d9a35-900a-48bb-9d3e-57db6ac61c82"));
        mem.put("dhgate_common_minimal_459d9a35-900a-48bb-9d3e-57db6ac61c82", "432",new Date(System.currentTimeMillis()+1));
        System.out.println(mem.getData("dhgate_common_minimal_459d9a35-900a-48bb-9d3e-57db6ac61c82"));
       
        mem.stop();
    }

    private void set(String key, String value, Date expiry) {
//        return cache.set(key, value, expiry);
    }
}

package com.isometric;

import com.isometric.entity.User;
import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

public class MemcachedHelper {
    //private static final Logger logger = LogManager.getLogger(MemcachedHelper.class);
    private static MemCachedClient client = null;

    static {
        String[] servers = {"localhost:11211"};
        SockIOPool pool = SockIOPool.getInstance("isometric");
        pool.setServers(servers);
        pool.setFailover(true);
        pool.setInitConn(10);
        pool.setMinConn(5);
        pool.setMaxConn(250);
        pool.setMaintSleep(30);
        pool.setNagle(false);
        pool.setSocketTO(3000);
        pool.setAliveCheck(true);
        pool.initialize();
        client = new MemCachedClient("isometric");
    }

    public static boolean putInCache(String key, Object value) {
        System.out.println("Cache In. " + key);
        return client.set(key, value);
    }

    public static Object getFromCache(String key) {
        Object value = client.get(key);
        if (value == null) {
            //logger.info("Cache miss for key:{}", key);
            System.out.println("Cache Miss. " + key);
            return null;

        } else {
            //logger.info("Cache hit for key:{}", key);
            System.out.println("Cache Hit. " + key);
            return value;
        }
    }

    public static void removeFromCache(String key) {
        client.delete(key);
        //logger.info("Removing key: {} from cache", key);
        System.out.println("Cache Remove. " + key);
    }

}

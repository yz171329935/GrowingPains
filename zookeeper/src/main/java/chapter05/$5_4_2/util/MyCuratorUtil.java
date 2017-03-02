package chapter05.$5_4_2.util;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by marco on 16/3/28.
 */
public class MyCuratorUtil {

    private static CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("192.168.224.54:2181")
            .retryPolicy(new ExponentialBackoffRetry(1000, 3))
            .build();

    public static CuratorFramework getClient(){
        return client;
    }
}

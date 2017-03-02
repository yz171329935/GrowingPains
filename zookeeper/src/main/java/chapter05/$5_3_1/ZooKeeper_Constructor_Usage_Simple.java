package chapter05.$5_3_1;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.concurrent.CountDownLatch;

/**
 * Created by marco on 16/3/25.
 */
public class ZooKeeper_Constructor_Usage_Simple implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    public void process(WatchedEvent event) {
        System.out.println("Receive watched event:" + event);
        if (event.getState() == Event.KeeperState.SyncConnected) {
            connectedSemaphore.countDown();
        }
    }

    public static void main(String[] args) throws Exception {
        ZooKeeper zooKeeper =
                new ZooKeeper("192.168.224.54:2181", 5000, new ZooKeeper_Constructor_Usage_Simple());
        System.out.println(zooKeeper.getState());
        zooKeeper.exists("/zoo",true);
        try {
            connectedSemaphore.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("zookeeper session established ~");
        Thread.sleep(Integer.MAX_VALUE);
    }
}

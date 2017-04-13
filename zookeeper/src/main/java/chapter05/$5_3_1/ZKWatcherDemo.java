package chapter05.$5_3_1;

import org.apache.zookeeper.*;

import java.util.concurrent.CountDownLatch;

/**
 * Created by marco on 16/3/25.
 */
public class ZKWatcherDemo implements Watcher {

    private static CountDownLatch connectedSemaphore = new CountDownLatch(1);

    private static ZooKeeper zooKeeper;

    private static final String path = "/zoo";

    public void process(WatchedEvent event) {
        System.out.println("Receive watched event:" + event);
        if (event.getState() == Event.KeeperState.SyncConnected) {
            try {
                zooKeeper.exists(path, true);
                if(event.getType() == Event.EventType.None && event.getPath() == null){
                    connectedSemaphore.countDown();
                }else if(event.getType() == Event.EventType.NodeCreated){

                }else if(event.getType() == Event.EventType.NodeDataChanged){

                }else if(event.getType() == Event.EventType.NodeChildrenChanged){

                }else if(event.getType() == Event.EventType.NodeDeleted){

                }
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        zooKeeper =
                new ZooKeeper("127.0.0.1:2181", 5000, new ZKWatcherDemo());
        System.out.println(zooKeeper.getState());
        try {
            connectedSemaphore.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

//        String path = "/zoo";
//        zooKeeper.exists(path, true);
//        zooKeeper.create(path, "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        zooKeeper.setData(path, "alala test".getBytes(), -1);
//
//        zooKeeper.create(path + "1", "".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
//        zooKeeper.setData(path + "1", "alala test".getBytes(), -1);
//
//        zooKeeper.delete(path, -1);
//        zooKeeper.delete(path + "1", -1);
//

        final String path2 = "/zooooo";

        Watcher watcher = new Watcher() {
            public void process(WatchedEvent event) {
                try {
                    System.out.println("Receive 2222 watched event:" + event);

                    zooKeeper.exists(path2,this);
                    zooKeeper.getChildren(path2,this);
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        zooKeeper.exists(path2,watcher);
        zooKeeper.getChildren(path2,watcher);

        zooKeeper.create(path2,"".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);


        Thread.sleep(Integer.MAX_VALUE);

    }
}

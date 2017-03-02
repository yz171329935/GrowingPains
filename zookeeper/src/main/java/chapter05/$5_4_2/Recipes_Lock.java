package chapter05.$5_4_2;

import chapter05.$5_4_2.util.MyCuratorUtil;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by marco on 16/3/28.
 */
public class Recipes_Lock {

    private static final String lock_path = "/curator_recipes_lock_path";


    public static void main(String[] args) {
        MyCuratorUtil.getClient().start();
        final InterProcessMutex lock = new InterProcessMutex(MyCuratorUtil.getClient(), lock_path);
        final CountDownLatch down = new CountDownLatch(1);

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {

                    try {
                        down.await();
                        lock.acquire();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                    String orderNo = sdf.format(new Date());
                    System.out.println("生成的订单号："+orderNo);

                    try {
                        lock.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        down.countDown();
    }
}

package chapter05.$5_4_2;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by marco on 16/3/28.
 */
public class Recipes_NoLock {

    public static void main(String[] args) {

        final CountDownLatch down = new CountDownLatch(1);

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        down.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss|SSS");
                    String orderNo = sdf.format(new Date());
                    System.out.println("生成的订单号："+orderNo);
                }
            }).start();
        }
        down.countDown();
    }
}

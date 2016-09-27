package bridge.product;

/**
 * Created by marco on 15/12/30.
 */
public class Ipod extends Product {
    @Override
    public void beProducted() {
        System.out.println("生产出的iPad是这个样子的...");
    }

    @Override
    public void beSelled() {
        System.out.println("生产出的iPad卖出去了...");
    }
}

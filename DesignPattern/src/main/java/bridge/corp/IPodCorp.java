package bridge.corp;

import bridge.product.Ipod;

/**
 * Created by marco on 15/12/30.
 */
public class IPodCorp extends Corp {

    public IPodCorp(Ipod product) {
        super(product);
    }

    //狂赚钱
    public void makeMoney(){
        super.makeMoney();
        System.out.println("我赚钱呀...");
    }
}

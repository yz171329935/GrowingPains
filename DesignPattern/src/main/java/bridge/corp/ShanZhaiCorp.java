package bridge.corp;

import bridge.product.Product;

/**
 * Created by marco on 15/12/30.
 */
public class ShanZhaiCorp extends Corp {

    //产什么产品,不知道,等被调用的才知道
    public ShanZhaiCorp(Product product){
        super(product);
    }

    //狂赚钱
    public void makeMoney(){
        super.makeMoney();
        System.out.println("山寨 我赚钱呀...");
    }

}

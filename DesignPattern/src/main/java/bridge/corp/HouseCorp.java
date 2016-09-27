package bridge.corp;

import bridge.product.House;

/**
 * Created by marco on 15/12/30.
 */
public class HouseCorp extends Corp {

    public HouseCorp(House product) {
        super(product);
    }


    //房地产公司很High了,赚钱,计算利润
    public void makeMoney(){
        super.makeMoney();
        System.out.println("房地产公司赚大钱了...");
    }
}

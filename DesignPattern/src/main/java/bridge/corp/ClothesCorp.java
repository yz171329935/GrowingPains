package bridge.corp;

import bridge.product.Clothes;

/**
 * Created by marco on 15/12/30.
 * 服装公司,这个行当现在不怎么样
 */
public class ClothesCorp extends Corp {

    public ClothesCorp(Clothes product) {
        super(product);
    }

    //服装公司不景气,但怎么说也是赚钱行业也
    public void makeMoney(){
        super.makeMoney();
        System.out.println("服装公司赚小钱...");
    }
}

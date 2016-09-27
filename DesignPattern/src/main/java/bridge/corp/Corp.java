package bridge.corp;

import bridge.product.Product;

/**
 * Created by marco on 15/12/30.
 * 定义一个公司的抽象类
 */
public abstract class Corp {

    private Product product;

    //构造函数,由子类定义传递具体的产品进来
    public Corp(Product product) {
        this.product = product;
    }
    

    //公司是干什么的?赚钱的呀,不赚钱傻子才干
    public void makeMoney(){
        //每个公司都是一样,先生产
        this.product.beProducted();
        //然后销售
        this.product.beSelled();
    }

}

package bridge;

import bridge.corp.ClothesCorp;
import bridge.corp.HouseCorp;
import bridge.corp.ShanZhaiCorp;
import bridge.product.Clothes;
import bridge.product.House;

/**
 * Created by marco on 15/12/30.
 */
public class Client {
    public static void main(String[] args) {

        System.out.println("-------房地产公司是这个样子运行的-------");
        //先找到我的公司
        HouseCorp houseCorp =new HouseCorp(new House());
        //看我怎么挣钱
        houseCorp.makeMoney();

        System.out.println("\n");

        System.out.println("-------服装公司是这样运行的-------");
        ClothesCorp clothesCorp = new ClothesCorp(new Clothes());
        clothesCorp.makeMoney();

        System.out.println("\n");

        System.out.println("-------山寨公司是这样运行的-------");
        ShanZhaiCorp shanZhaiCorp = new ShanZhaiCorp(new Clothes());
        shanZhaiCorp.makeMoney();
    }
}

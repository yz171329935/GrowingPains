package observer;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by marco on 16/1/26.
 */
public class ObserverLiSi2 implements Observer {

    //首先李斯2是个观察者,一旦韩非子有活动,他就知道,他就要向老板汇报
    @Override
    public void update(Observable o, Object str) {
        System.out.println("李斯2:观察到李斯活动,开始向老板汇报了...");
        this.reportToQiShiHuang((String)str);
        System.out.println("李斯2:汇报完毕,秦老板赏给他两个萝卜吃吃...\n");
    }

    //汇报给秦始皇
    private void reportToQiShiHuang(String reportContext){
        System.out.println("李斯2:报告,秦老板!韩非子有活动了--->"+reportContext);
    }
}

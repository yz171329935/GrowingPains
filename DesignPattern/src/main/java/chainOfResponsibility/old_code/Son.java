package chainOfResponsibility.old_code;

import chainOfResponsibility.IWomen;

/**
 * Created by marco on 16/1/14.
 */
public class Son implements IHandler{

    //目前向儿子请示
    @Override
    public void HandleMessage(IWomen women) {
        System.out.println("母亲的请示是:"+women.getRequest());
        System.out.println("儿子的答复是:同意");
    }

}

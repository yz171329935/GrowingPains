package chainOfResponsibility.old_code;

import chainOfResponsibility.IWomen;

/**
 * Created by marco on 16/1/14.
 */
public class Father implements IHandler{

    //未出嫁女儿来请示父亲
    @Override
    public void HandleMessage(IWomen women) {
        System.out.println("女儿的请示是:"+women.getRequest());
        System.out.println("父亲的答复是:同意");
    }

}

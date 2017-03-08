package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by sun on 17/3/8.
 */
public class WangPoHandler implements InvocationHandler {

    Object obj;
    WangPoHandler(Object obj){
        this.obj = obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
            this.doBefore();
            Object o = method.invoke(obj, args);
            this.doAfter();
            return o;

    }

    public void doBefore(){
        //
        System.out.println("前面做的事情");
    }

    public void doAfter(){
        //
        System.out.println("后面做的事情");
    }
}

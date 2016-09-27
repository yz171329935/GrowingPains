package singleton;

/**
 * Created by marco on 15/12/24.
 * 最简单也最保险的单例模式
 */
public class Singleton {

// ps:  a.所有静态的(无论其是变量还是块)都按顺序执行.
//      b.所有的块(无论是静态的还是非静态的)都在构造之前执行.
//      c.非静态块是在创建对象时，构造之前被调用

    //  静态块在类被加载到内存后就开始执行
    public static final Singleton instance = new Singleton();

    //限制住不能直接产生一个实例
    private Singleton() {}

    public synchronized static Singleton getInstance(){ return instance;}

}

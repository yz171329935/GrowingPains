package proxy;

/**
 * Created by marco on 15/12/23.
 * 王婆这个人老聪明了,她太老了,是个男人都看不上,
 * 但是她有智慧有经验呀,她作为一类女人的代理!
 */
public class WangPo implements KindWomen {

    private KindWomen kindWomen;

    public WangPo() {
        this.kindWomen = new PanJinLian();
    }

    public WangPo(KindWomen kindWomen) {
        this.kindWomen = kindWomen;
    }


    public void makeEyesWithMan() {
        this.kindWomen.makeEyesWithMan(); //王婆这么大年龄了,谁看她抛媚眼?!
    }


    public void happyWithMan() {
        kindWomen.happyWithMan(); //自己老了,干不了,可以让年轻的代替
    }
}

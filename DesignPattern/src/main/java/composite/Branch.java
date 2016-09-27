package composite;

import java.util.ArrayList;

/**
 * Created by marco on 16/1/13.
 */
public class Branch extends Corp implements IBranch {

    //存储子节点的信息
    private ArrayList<Corp> subordinateList = new ArrayList<Corp>();

    //通过构造函数传递树枝节点的参数
    public Branch(String name,String position,int salary){
       super(name,position,salary);
    }

    @Override
    public void addSubordinate(Corp corp) {
        this.subordinateList.add(corp);
    }

    @Override
    public  ArrayList<Corp> getSubordinateInfo() {
        return this.subordinateList;
    }
}

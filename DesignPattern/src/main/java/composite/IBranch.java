package composite;

import java.util.ArrayList;

/**
 * Created by marco on 16/1/13.
 * 树枝节点,也就是各个部门经理和组长的角色
 */
public interface IBranch {

    //能够增加小兵(树叶节点)或者是经理(树枝节点)
    public void addSubordinate(Corp corp);

    //获得下级信息
    public  ArrayList<Corp>  getSubordinateInfo();

    /**
     *  还应该有一个方法delSubordinate(ICorp corp),
     *  删除下属 * 这个方法我们没有用到就不写进来了
    */
}

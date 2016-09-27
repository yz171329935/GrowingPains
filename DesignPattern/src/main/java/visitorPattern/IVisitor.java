package visitorPattern;

/**
 * Created by marco on 16/3/3.
 * 访问者,我要去访问人家的数据了
 */
public interface IVisitor {
    //首先定义我可以访问普通员工
    public void visit(CommonEmployee commonEmployee);

    //其次定义,我还可以访问部门经理
    public void visit(Manager manager);
}

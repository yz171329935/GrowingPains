package visitorPattern;

/**
 * Created by marco on 16/3/3.
 * 经理级人物
 */
public class Manager extends Employee {
    //这类人物的职责非常明确:业绩
    private String performance;

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    protected String getOtherInfo() {
        return "业绩:" + this.performance + "\t";
    }

    //部门经理允许访问者访问
    @Override
    public void accept(IVisitor visitor){
        visitor.visit(this);
    }
}

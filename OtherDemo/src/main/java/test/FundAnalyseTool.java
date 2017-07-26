package test;

import com.google.common.collect.Lists;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by marco on 2017/7/14.
 * 基金回撤率 计算
 */

public class FundAnalyseTool {

    public static BigDecimal analyse(List<FundNetValue> netValues) {
            if (netValues == null || netValues.size() == 0) {
                return BigDecimal.ZERO;
            }
            Collections.sort(netValues, new Comparator<FundNetValue>() {
                public int compare(FundNetValue o1, FundNetValue o2) {
                    return o1.getNetValueDate().before(o2.getNetValueDate()) ? -1 : 1;
                }
            });
            BigDecimal mixRate = BigDecimal.ZERO;
            for (int i = 0; i < netValues.size(); i++) {
                FundNetValue value_i = netValues.get(i);
                for (int j = 0; j < netValues.size(); j++) {
                    if (i < j) {
                        FundNetValue value_j = netValues.get(j);
                        BigDecimal currentRate = value_i.getNetValue().subtract(value_j.getNetValue())
                                .divide(value_i.getNetValue(),2,BigDecimal.ROUND_HALF_UP);
                        if (mixRate.compareTo(currentRate) == -1) {
                            mixRate = currentRate;
                        }
                    }
                }
            }
            BigDecimal oneHundred  = new BigDecimal("100");
            return mixRate.multiply(oneHundred);
        }

    public static void main(String[] args) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date  data1 = sdf.parse("2017-07-01");
        Date  data2 = sdf.parse("2017-07-02");
        Date  data3 = sdf.parse("2017-07-03");
        Date  data4 = sdf.parse("2017-07-04");

        FundNetValue netValue1 = new FundNetValue(data1,new BigDecimal("300"));
        FundNetValue netValue2 = new FundNetValue(data2,new BigDecimal("400"));
        FundNetValue netValue3 = new FundNetValue(data3,new BigDecimal("100"));
        FundNetValue netValue4 = new FundNetValue(data4,new BigDecimal("200"));
        List<FundNetValue> netValues = Lists.newArrayList(netValue4,netValue2,netValue1,netValue3);

        BigDecimal  maxRate = FundAnalyseTool.analyse(netValues);
        System.out.printf("最大回撤率为： " + maxRate );
    }
}

class FundNetValue {
    /** 净值日期 */
    private Date netValueDate;

    /** 净值 */
    private BigDecimal netValue;

    /** 构造函数 */
    public FundNetValue(Date netValueDate, BigDecimal netValue) {
        this.netValueDate = netValueDate;
        this.netValue = netValue;
    }

    /** setter for netValueDate */
    public void setNetValueDate(Date netValueDate) {
        this.netValueDate = netValueDate;
    }

    /** setter for netValue */
    public void setNetValue(BigDecimal netValue) {
        this.netValue = netValue;
    }

    /** tetter for netValueDate */
    public Date getNetValueDate() {
        return this.netValueDate;
    }

    /** getter for netValue */
    public BigDecimal getNetValue() {
        return this.netValue;
    }
}
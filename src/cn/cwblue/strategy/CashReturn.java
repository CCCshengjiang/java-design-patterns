package cn.cwblue.strategy;

/**
 * 返利收费
 *
 * @author wen
 */
public class CashReturn extends CashSuper{
    //返利条件
    private double moneyCondition = 0;
    //返利值
    private double moneyReturn = 0;

    public CashReturn(double moneyCondition, double moneyReturn) {
        this.moneyCondition = moneyCondition;
        this.moneyReturn = moneyReturn;
    }
    @Override
    public double acceptCash(double price, int num) {
        double res = price * num;
        if (moneyCondition > 0 && res >= moneyCondition) {
            res = res - Math.floor(res / moneyCondition) * moneyReturn;
        }
        return res;
    }
}

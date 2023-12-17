package cn.cwblue.decorator.improved;

/**
 * 具体的装饰类——返利
 *
 * @author wen
 */
public class CashReturn extends CashSuper{
    double moneyCondition = 0;
    double moneyReturn = 0;
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
        return super.acceptCash(res, 1);
    }
}

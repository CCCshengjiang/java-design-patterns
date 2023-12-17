package cn.cwblue.decorator.improved;

/**
 * 具体的装饰类——打八折
 *
 * @author wen
 */
public class CashRebate extends CashSuper{
    double moneyRebate = 1;
    public CashRebate(double moneyRebate) {
        this.moneyRebate = moneyRebate;
    }
    @Override
    public double acceptCash(double price, int num) {
        double res = price * moneyRebate * num;
        return super.acceptCash(res, 1);
    }
}

package cn.cwblue.strategy;

/**
 * 打折收费
 *
 * @author wen
 */
public class CashRebate extends CashSuper{
    private double moneyRebate = 1;
    //初始化时候要输入打折率
    public CashRebate(double moneyRebate) {
        this.moneyRebate = moneyRebate;
    }

    @Override
    public double acceptCash(double price, int num) {
        return price * moneyRebate * num;
    }
}

package cn.cwblue.strategy;

/**
 * 正常收费
 *
 * @author wen
 */
public class CashNormal extends CashSuper{
    @Override
    public double acceptCash(double price, int num) {
        return price * num;
    }
}

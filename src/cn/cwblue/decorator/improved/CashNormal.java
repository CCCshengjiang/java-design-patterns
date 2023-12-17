package cn.cwblue.decorator.improved;

/**
 * 要装饰的对象
 *
 * @author wen
 */
public class CashNormal implements ISale{
    @Override
    public double acceptCash(double price, int num) {
        return price * num;
    }
}

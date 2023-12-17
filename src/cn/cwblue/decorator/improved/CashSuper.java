package cn.cwblue.decorator.improved;

/**
 * 装饰抽象类
 *
 * @author wen
 */
public class CashSuper implements ISale{
    protected ISale component;
    public void decorator(ISale component) {
        this.component = component;
    }
    @Override
    public double acceptCash(double price, int num) {
        double res = 0;
        if (component != null) {
            res = component.acceptCash(price, num);
        }
        return res;
    }
}

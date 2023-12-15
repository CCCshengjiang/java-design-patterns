package cn.cwblue.strategy;

/**
 * 上下文
 *
 * @author wen
 */
public class CashContext {
    private CashSuper cashSuper;
    public CashContext(int  cashType) {
        switch (cashType) {
            case 1:  //正常收费
                cashSuper = new CashNormal();
                break;
            case 2:  //打八折收费
                cashSuper = new CashRebate(0.8);
                break;
            case 3:  //满300返利100
                cashSuper = new CashReturn(300, 100);
                break;
        }
    }

    public double getResult(double price, int num) {
        return cashSuper.acceptCash(price, num);
    }
}

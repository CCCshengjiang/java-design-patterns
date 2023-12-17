package cn.cwblue.decorator.improved;

public class CashContext {
    private ISale component;

    public CashContext(int cashType) {
        switch (cashType) {
            case 1:
                component = new CashNormal();
                break;
            case 2:
                CashNormal cashNormal2 = new CashNormal();
                CashRebate cashRebate2 = new CashRebate(0.8);
                cashRebate2.decorator(cashNormal2);
                component = cashRebate2;
                break;
            case 3:
                CashNormal cashNormal3 = new CashNormal();
                CashReturn cashReturn3 = new CashReturn(300, 100);
                cashReturn3.decorator(cashNormal3);
                component = cashReturn3;
                break;
            case 4:
                CashNormal cashNormal4 = new CashNormal();
                CashReturn cashReturn4 = new CashReturn(300, 100);
                CashRebate cashRebate4 = new CashRebate(0.8);
                cashReturn4.decorator(cashNormal4);
                cashRebate4.decorator(cashReturn4);
                component = cashReturn4;
                break;
        }
    }
    public double getResult(double price, int num) {
        return component.acceptCash(price, num);
    }
}

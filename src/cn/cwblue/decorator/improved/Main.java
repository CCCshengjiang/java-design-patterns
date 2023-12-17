package cn.cwblue.decorator.improved;

import java.util.Scanner;

/**
 * 简单工厂模式 + 策略模式 + 装饰模式实现商场收银
 *
 * @author wen
 */
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入当前销售模式：1：无促销，2：打八折，3：满300返100, 4：先打八折再返利");
        int cashType = Integer.parseInt(sc.nextLine()); //商品销售模式
        System.out.println("请输入当前商品单价：");
        double price = Double.parseDouble(sc.nextLine()); //商品单价
        System.out.println("请输入当前商品数量：");
        int num = Integer.parseInt(sc.nextLine()); //商品数量
        double totalPrice = 0; //当前商品价格

        totalPrice = new CashContext(cashType).getResult(price, num);

        System.out.println("商品总价为：" + totalPrice + "元");
    }
}

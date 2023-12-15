package cn.cwblue.factory.simple;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
       try {
           Scanner sc = new Scanner(System.in);
           System.out.println("请输入第一个数字：");
           double numA = Double.parseDouble(sc.nextLine());
           System.out.println("请选择运算符号（+、-、*、/）：");
           String strOperate = sc.nextLine();
           System.out.println("test:" + strOperate);
           System.out.println("请输入第二个数字：");
           double numB = Double.parseDouble(sc.nextLine());

           double res = OperationFactory.createOperation(strOperate).getResult(numA, numB);

           System.out.println("结果是：" + res);
       }catch (Exception e) {
           System.out.println("输入格式有误：" + e);
       }
    }
}

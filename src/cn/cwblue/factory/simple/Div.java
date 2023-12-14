package cn.cwblue.factory.simple;

public class Div extends Operation{
    @Override
    public double getResult(double numA, double numB) {
        if (numB == 0) {
            System.out.println("除数不能为0！");
            throw new ArithmeticException();
        }
        return numA / numB;
    }
}

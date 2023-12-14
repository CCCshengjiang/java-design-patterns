package cn.cwblue.factory.simple;

public class Sub extends Operation{
    @Override
    public double getResult(double numA, double numB) {
        return numA - numB;
    }
}

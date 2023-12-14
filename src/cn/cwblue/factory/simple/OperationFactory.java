package cn.cwblue.factory.simple;

public class OperationFactory {
    public static Operation createOperation(String strOperation) {
        Operation operation = null;
        switch (strOperation) {
            case "+":
                operation = new Add();
                break;
            case "-":
                operation = new Sub();
                break;
            case "*":
                operation = new Mul();
                break;
            case "/":
                operation = new Div();
                break;
        }
        return operation;
    }
}

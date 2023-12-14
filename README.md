# 工厂模式

## 简单工厂模式

现在我们要实现一个简单的计算器，输入两个数字，计算器能够完成加减乘除运算。

```java
public class SimpleFactory {
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
           double res = 0;
           switch (strOperate) {
               case "+":
                   res = numA + numB;
                   break;
               case "-":
                   res = numA - numB;
                   break;
               case "*":
                   res = numA * numB;
                   break;
               case "/":
                   res = numA / numB;
                   break;
           }
           System.out.println("结果是：" + res);
       }catch (Exception e) {
           System.out.println("输入格式有误：" + e);
       }
    }
}
```



虽然我们实现了计算器的功能，但是可以发现一个问题：我们写出来的程序只是用计算机的方式去思考，而没有用到面向对象的思想。也就是说我们的程序只是满足了当前的需求，但是它**不容易维护、不容易扩展、更不容易复用**，所以这个代码还达不到高质量的要求。

假如我们需要把这个代码复用一下，就需要整个Ctrl+c，虽然现在看不出来改动有多麻烦，但是当代码多到一定程度，此时维护起来就会是一场灾难了。那么现在我们加入使用**面向对象的封装的思想**，让程序变得可复用。

我们写出来的程序可以分为两个部分：

1. 界面逻辑，负责接收输入的参数和要运算的操作
2. 业务逻辑，负责处理具体的运算操作

改造后的代码：

处理业务逻辑类：

```java
public class Operation {
    public static double getResult(double numA, double numB, String strOperate) {
        double res = 0;
        switch (strOperate) {
            case "+":
                res = numA + numB;
                break;
            case "-":
                res = numA - numB;
                break;
            case "*":
                res = numA * numB;
                break;
            case "/":
                res = numA / numB;
                break;
        }
        return res;
    }
}
```

界面逻辑类：

```java
public class SimpleFactory {
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
           double res = Operation.getResult(numA, numB, strOperate);
           System.out.println("结果是：" + res);
       }catch (Exception e) {
           System.out.println("输入格式有误：" + e);
       }
    }
}
```



我们将程序的一些方法和执行步骤隐藏起来，只开放外部接口。就好像一辆车，引擎就是封装好的部件，我们不需要知道引擎的工作原理，只需要知道踩油门就会发送指令，车子就会走是一样的。现在的程序不管是在web程序还是app程序，都可以复用这个运算类（Operation类）。

现在的程序虽然做到了可复用，但是还没有达到可以灵活的修改和扩展，如果我要加一个指数的运算，就意味着我需要在原来的代码中去修改。此时我们可以利用**面向对象的继承思想**来修改代码，让其变得可灵活修改和扩展。

改造后的代码：

Operation运算类：

```java
public class Operation {
    public double getResult(double numA, double numB) {
        return 0;
    }
}
```

加减乘除类：

```java
public class Add extends Operation{
    @Override
    public double getResult(double numA, double numB) {
        return numA + numB;
    }
}
```

```java
public class Sub extends Operation{
    @Override
    public double getResult(double numA, double numB) {
        return numA - numB;
    }
}
```

```java
public class Mul extends Operation{
    @Override
    public double getResult(double numA, double numB) {
        return numA * numB;
    }
}
```

```java
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
```



上述程序中的加减乘除操作都继承了运算抽象类，并重写了其中的运算方法。但是这样就有一个问题了，怎么让计算器知道需要创建的是哪个对象呢？

接下来进入正题：简单工厂模式。在上述程序中，我们只要根据用户输入的运算操作，来实例化对应的运算类对象。那么怎么才能让计算器自己根据运算符去实例化对象，且不会增加多余的实例化对象。我们可以用一个单独的类来做这个创造实例的过程。

改造后的代码：

简单运算工厂类：

```java
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
```

界面逻辑类：

```java
public class SimpleFactory {
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
```



上述程序，使用了简单工厂和多态来让程序自己根据运算符实例化对象。现在我们的代码不管是在控制台、web程序、App程序都可以用，如果我们需要增加别的运算操作，只需要增加对应的运算子类和增加运算工厂类中的switch分支。



**上述程序结构图：**

![简单工厂模式类的结构图](https://gitee.com/CCCshengjiang/blog-img/raw/master/image/202312141424340.png)
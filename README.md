# UML样图

关于UML，不做过多说明了，直接看一组很全的样图。之后的程序UML图都是参考此图。

![UML类图样例](https://gitee.com/CCCshengjiang/blog-img/raw/master/image/202312162125077.jpg)

# 工厂模式

## 简单工厂模式—计算器

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





# 策略模式—商场促销

策略模式定义了算法家族，分别封装起来，让他们之间可以互相替换，此模式让算法的变化不会影响到使用算法的客户。

现在有一个商场需要一个收银系统，根据客户所买商品的单价和数量来收费。商场总共有三种销售模式：

1. 第一种是全部商品原价收费
2. 第二种是全部打八折处理
3. 第三种是满300返利100



> 此时可以根据简单工厂模式，收银员只需要输入当前销售模式，让程序自己判断使用哪种计算方式。

**抽象收费类：**

```java
public abstract class CashSuper {
    public abstract double acceptCash(double price, int num);
}
```



**正常收费类：**

```java
public class CashNormal extends CashSuper{
    @Override
    public double acceptCash(double price, int num) {
        return price * num;
    }
}
```



**打折收费类：**

```java
public class CashRebate extends CashSuper{
    private double moneyRebate = 1;
    //初始化时候要输入打折率
    public CashRebate(double moneyRebate) {
        this.moneyRebate = moneyRebate;
    }

    @Override
    public double acceptCash(double price, int num) {
        return price * moneyRebate * num;
    }
}
```



**返利收费类：**

```java
public class CashReturn extends CashSuper{
    //返利条件
    private double moneyCondition = 0;
    //返利值
    private double moneyReturn = 0;

    public CashReturn(double moneyCondition, double moneyReturn) {
        this.moneyCondition = moneyCondition;
        this.moneyReturn = moneyReturn;
    }
    @Override
    public double acceptCash(double price, int num) {
        double res = price * num;
        if (moneyCondition > 0 && res >= moneyCondition) {
            res = res - Math.floor(res / moneyCondition) * moneyReturn;
        }
        return res;
    }
}
```



**收费对象生成工厂：**

```java
public class CashFactory {
    public static CashSuper createCashAccept(int cashType) {
        CashSuper cashSuper = null;
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
        return cashSuper;
    }
}
```



**界面逻辑：**

```java
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入当前销售模式：1：无促销，2：打折，3：返利");
        int cashType = Integer.parseInt(sc.nextLine()); //商品销售模式
        System.out.println("请输入当前商品单价：");
        double price = Double.parseDouble(sc.nextLine()); //商品单价
        System.out.println("请输入当前商品数量：");
        int num = Integer.parseInt(sc.nextLine()); //商品数量
        double totalPrice = 0; //当前商品价格

        CashSuper cashSuper = CashFactory.createCashAccept(cashType);
        totalPrice = cashSuper.acceptCash(price, num);

        System.out.println("商品总价为：" + totalPrice + "元");
    }
}
```



**当前程序结构图：**

![image-20231215195127230](https://gitee.com/CCCshengjiang/blog-img/raw/master/image/202312151951290.png)



> 虽然此时我们解决了对象的创建问题，但是商场是一个经常性更改折扣额度和返利额度的地方，如果每次都要重新编译部署，这种方式实在是太繁琐了，所以简单工厂不是最好的，这个时候就应该使用策略模式，让这些促销方式封装起来，让他们之间可以相互替换。

改造简单工厂为策略模式，首先把工厂类删除，改造后的程序：

**新增收费上下文类：**

```java
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
```



**界面逻辑改造：**

```java
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入当前销售模式：1：无促销，2：打八折，3：满300返100");
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
```



**当前程序结构图：**

![image-20231215203555097](https://gitee.com/CCCshengjiang/blog-img/raw/master/image/202312152035150.png)



> 简单工厂模式：我需要让界面也就是客户端，认识两个类：CashSuper和CashFactory
>
> 策略模式与简单工厂结合：客户端只需要认识一个类：CashContext



**总结：**

策略模式是**定义一系列算法的方法**，从用途上，他们完成的是相同的工作，只是具体的实现有所不同，优点：

1. 策略模式可以用相同的方式调用所有的算法，减少了各类算法类与使用算法类的耦合。
2. 而且策略模式可以对每个算法类进行单独的测试，同时修改任何一个算法类也不影响其它算法类。

总的来说，策略模式封装了变化，只要在分析过程中需要再不同的时间或者场景下用不同的规则，此时就可以考虑用策略模式实现的可能性。



# 装饰模式—穿衣服

装饰模式（Decorator）可以动态的给对象添加一些额外的职责。

![装饰模式结构图](https://gitee.com/CCCshengjiang/blog-img/raw/master/image/202312180009702.png)

> Component是定义一个对象接口，可以给这些对象动态地添加职责。ConcreteComponent是定义了一个具体的对象，也可以给这个对象添加一些职责。Decorator，装饰抽象类，从外类来扩展Component类的功能，但是对于Component来说，是不需要知道Decorator的存在。



现在有一个装扮游戏，需要你给主角穿衣服，最后只需要在控制台打印出来穿的什么衣服就行。利用装饰模式的思想，有一个接口可以给对象动态的添加职责；有一个具体的要装饰的人，还要有一个装饰的抽象类，所有具体的装饰类都继承这个装饰抽象类。

**Component对象接口：**

```java
public interface ICharacter {
    void show();
}
```



**具体的对象：**

```java
public class Person implements ICharacter{
    private String name;
    public Person(String name) {
        this.name = name;
    }
    @Override
    public void show() {
        System.out.println("装扮的" + name);
    }
}
```



**装饰抽象类：**

```java
public abstract class Finery implements ICharacter{
    protected ICharacter component;
    public void decorate(ICharacter component) {
        this.component = component;
    }

    @Override
    public void show() {
        if (component != null) {
            this.component.show();
        }
    }
}
```



**具体的装饰类：**

运动裤

```java
public class SweatPants extends Finery{
    @Override
    public void show() {
        System.out.print("运动裤");
        super.show();
    }
}
```

T恤衫：

```java
public class TShirt extends Finery{

    @Override
    public void show() {
        System.out.print("T恤衫");
        super.show();
    }
}
```

西装：

```java
public class Suit extends Finery{
    @Override
    public void show() {
        System.out.print("西装");
        super.show();
    }
}
```

皮鞋：

```java
public class LeatherShoes extends Finery{
    @Override
    public void show() {
        System.out.print("皮鞋");
        super.show();
    }
}
```



**客户端代码：**

```java
public class Main {
    public static void main(String[] args) {
        Person wen = new Person("小文");
        System.out.println("第一种装扮：");
        TShirt tShirt = new TShirt();
        tShirt.decorate(wen);
        SweatPants sweatPants = new SweatPants();
        sweatPants.decorate(tShirt);
        sweatPants.show();

        System.out.println("第二种装扮：");
        Suit suit = new Suit();
        suit.decorate(wen);
        LeatherShoes leatherShoes = new LeatherShoes();
        leatherShoes.decorate(suit);
        leatherShoes.show();
    }
}
```





程序的UML图：

![穿衣服的程序UML图](https://gitee.com/CCCshengjiang/blog-img/raw/master/image/202312171609611.png)

> 总结：
>
> 装饰模式不需要向旧的类中添加新的装饰代码，它提供了一个非常好的解决方案，它把每个要装饰的功能放在单独的类中，让这个类包装它所需要的装饰对象。总而言之，装饰功能就是将类中的装饰功能从类中移除，可以简化原有的类。
>
> 装饰模式有效的把类的核心职责和装饰功能区分开了，而且可以去除相关类中重复的装饰逻辑。
>
> ![装饰模式理解](https://gitee.com/CCCshengjiang/blog-img/raw/master/image/202312171618663.png)



接下来，我们对之前写过的商场促销做一个升级，使用**简单工厂模式+策略模式+装饰模式**。商场收银之前只支持所有商品都打八折或者都有返利或者都是原价。现在需要加功能，就是在八折的基础上再进行返利活动。要求对之前的代码改动尽可能小。

升级后的代码：

**Component对象接口：**

```java
public interface ISale {
    double acceptCash(double price, int num);
}
```



**要装饰的对象：**

```java
public class CashNormal implements ISale{
    @Override
    public double acceptCash(double price, int num) {
        return price * num;
    }
}
```



**装饰抽象类：**

```java
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
```



**具体的装饰类：**

打八折：

```java
public class CashRebate extends CashSuper{
    double moneyRebate = 1;
    public CashRebate(double moneyRebate) {
        this.moneyRebate = moneyRebate;
    }
    @Override
    public double acceptCash(double price, int num) {
        double res = price * moneyRebate * num;
        return super.acceptCash(res, 1);
    }
}
```

返利：

```java
public class CashReturn extends CashSuper{
    double moneyCondition = 0;
    double moneyReturn = 0;
    public CashReturn(double moneyCondition, double moneyReturn) {
        this.moneyCondition = moneyCondition;
        this.moneyReturn = moneyReturn;
    }
    @Override
    public double acceptCash(double price, int num) {
        double res = price * num;
        if (moneyCondition > 0 && res >= moneyCondition) {
            res = res - Math.floor(res / moneyCondition) * moneyReturn;
        }
        return super.acceptCash(res, 1);
    }
}
```



**客户端代码：**

```java
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
```

![image-20231218001903878](https://gitee.com/CCCshengjiang/blog-img/raw/master/image/202312180019021.png)

**程序UML图：**

![image-20231218001832679](https://gitee.com/CCCshengjiang/blog-img/raw/master/image/202312180018837.png)



> 装饰模式的装饰顺序很重要，比如加密数据和过滤词汇都可以是持久化前的装饰功能，但若是先加密了数据在过滤就会出现问题。
package cn.cwblue.decorator;

/**
 * 装饰模式
 *
 * @author wen
 */
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

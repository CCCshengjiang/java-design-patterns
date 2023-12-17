package cn.cwblue.decorator;

public class LeatherShoes extends Finery{
    @Override
    public void show() {
        System.out.print("皮鞋");
        super.show();
    }
}

package cn.cwblue.decorator;

public class Suit extends Finery{
    @Override
    public void show() {
        System.out.print("西装");
        super.show();
    }
}

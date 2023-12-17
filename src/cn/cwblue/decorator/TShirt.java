package cn.cwblue.decorator;

public class TShirt extends Finery{

    @Override
    public void show() {
        System.out.print("T恤衫");
        super.show();
    }
}

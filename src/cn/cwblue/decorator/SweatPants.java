package cn.cwblue.decorator;

public class SweatPants extends Finery{
    @Override
    public void show() {
        System.out.print("运动裤");
        super.show();
    }
}

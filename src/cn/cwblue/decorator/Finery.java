package cn.cwblue.decorator;

/**
 * 装饰抽象类
 *
 * @author wen
 */
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

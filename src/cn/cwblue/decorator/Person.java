package cn.cwblue.decorator;

/**
 * 具体的要装饰的人
 *
 * @author wen
 */
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

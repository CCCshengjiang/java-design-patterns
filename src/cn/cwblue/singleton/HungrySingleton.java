package cn.cwblue.singleton;

/**
 * 饿汉式单例模式
 *
 * @author wen
 */
public class HungrySingleton {

    private static final HungrySingleton hungrySingleton = new HungrySingleton();

    private HungrySingleton() {}

    public static HungrySingleton getHungrySingleton() {
        return hungrySingleton;
    }

}

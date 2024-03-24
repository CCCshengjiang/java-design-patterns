package cn.cwblue.singleton;

/**
 * 懒汉式单例模式
 *
 * @author wen
 */
public class LazySingleton {

    private LazySingleton() {
    }

    private static class LazySingletonHolder {
        private static final LazySingleton LAZY_SINGLETON = new LazySingleton();
    }

    public static LazySingleton getLazySingleton() {
        return LazySingletonHolder.LAZY_SINGLETON;
    }

}

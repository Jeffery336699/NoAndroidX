package com.circle.drawable.mode.factroy.singleton;

/**
 * 静态内部类形式,线程安全+延迟加载
 */
public class Singleton04 {
    private static final class InstanceHolder {
        private static Singleton04 INSTANCE = new Singleton04();
    }

    public static Singleton04 getInstance() {
        return InstanceHolder.INSTANCE;
    }
}

package com.circle.drawable.mode.factroy.singleton;


/**
 * 枚举实现单例,解决线程安全和单一实例的问题，还可以防止反射和反序列化对单例的破坏
 */
public class Singleton3 {
    private Singleton3() {
    }

    public static enum SingletonEnum {
        SINGLETON;
        private Singleton3 instance = null;

        private SingletonEnum() {
            instance = new Singleton3();
        }

        public Singleton3 getInstance() {
            return instance;
        }
    }
}


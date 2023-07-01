package com.circle.drawable.mode.factroy.singleton;

/**
 * 懒汉式,双重判断
 */
public class Singleton02 {
    private static Singleton02 instance;

    public static Singleton02 getInstance() {
        if (instance == null) {
            synchronized (Singleton02.class) {
                if (instance == null) {
                    instance = new Singleton02();
                }
            }
        }
        return instance;
    }

}
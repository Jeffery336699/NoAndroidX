package com.circle.drawable.mode.factroy.singleton;

/**
 * 饿汉式
 */
public class Singleton {
    private static Singleton instance = new Singleton();

    public static Singleton getInstance() {
        return instance;
    }
}
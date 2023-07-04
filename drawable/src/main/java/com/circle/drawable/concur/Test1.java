package com.circle.drawable.concur;

public class Test1 {
    /**
     * 模拟爸爸去饭店
     */
    public static void fatherToRes() {
        System.out.println("爸爸步行去饭店需要3小时。");
    }

    /**
     * 模拟我去饭店
     */
    public static void motherToRes() {
        System.out.println("妈妈挤公交去饭店需要2小时。");
    }

    /**
     * 模拟妈妈去饭店
     */
    public static void meToRes() {
        System.out.println("我乘地铁去饭店需要1小时。");
    }

    /**
     * 模拟一家人到齐了
     */
    public static void togetherToEat() {
        System.out.println("一家人到齐了，开始吃饭");
    }

    public static void main(String[] args) {
        fatherToRes();
        motherToRes();
        meToRes();
        togetherToEat();
    }
}
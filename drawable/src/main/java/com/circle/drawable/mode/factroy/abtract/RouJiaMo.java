package com.circle.drawable.mode.factroy.abtract;


public abstract class RouJiaMo {
    public String name;
    /**
     * 准备工作
     */
    public final void prepare(RouJiaMoYLFactroy ylFactroy) {
        Meat meat = ylFactroy.createMeat();
        YuanLiao yuanliao = ylFactroy.createYuanliao();
        System.out.println("使用官方的原料" + meat + " , " + yuanliao + "作为原材料制作肉夹馍 ");
    }

    /**
     * 使用你们的专用袋-包装
     */
    public final void pack() {
        System.out.println("肉夹馍-专用袋-包装");
    }

    /**
     * 秘制设备-烘烤2分钟
     */
    public final void fire() {
        System.out.println("肉夹馍-专用设备-烘烤");
    }
}
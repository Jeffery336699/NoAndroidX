package com.circle.drawable.mode.factroy.abtract;

/**
 * 提供肉夹馍的原料
 * @author zhy
 * TODO 抽象工厂模式:提供一个接口，用于创建相关的或依赖对象的家族，而不需要明确指定具体类
 *      例如原材料如肉,其他配料等
 */
public interface RouJiaMoYLFactroy
{
    /**
     * 生产肉
     * @return
     */
    public Meat createMeat();

    /**
     * 生产调料神马的
     * @return
     */
    public YuanLiao createYuanliao();

}
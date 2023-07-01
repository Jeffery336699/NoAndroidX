package com.circle.drawable.mode.decorator;

/**
 * 装备的接口
 *
 * @author zhy
 */
public interface IEquip {

    /**
     * 计算攻击力
     *
     * @return
     */
    public int caculateAttack();

    /**
     * 装备的描述
     *
     * @return
     */
    public String description();
}
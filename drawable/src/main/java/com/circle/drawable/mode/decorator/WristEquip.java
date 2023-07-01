package com.circle.drawable.mode.decorator;

/**
 * 护腕
 * 攻击力 5
 * @author zhy
 *
 */
public class WristEquip implements IEquip
{

    @Override
    public int caculateAttack()
    {
        return 5;
    }

    @Override
    public String description()
    {
        return "圣战护腕";
    }

}
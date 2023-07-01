package com.circle.drawable.mode.decorator;

/**
 * 鞋子
 * 攻击力 5
 * @author zhy
 *
 */
public class ShoeEquip implements IEquip
{

    @Override
    public int caculateAttack()
    {
        return 5;
    }

    @Override
    public String description()
    {
        return "圣战靴子";
    }

}
package com.circle.drawable.mode.decorator;

/**
 * 武器
 * 攻击力20
 * @author zhy
 *
 */
public class ArmEquip implements IEquip
{

    @Override
    public int caculateAttack()
    {
        return 20;
    }

    @Override
    public String description()
    {
        return "屠龙刀";
    }

}
package com.circle.drawable.mode.factroy.abtract;

/**
 * 根据西安当地特色，提供这两种材料
 * @author zhy
 *
 */
public class XianRouJiaMoYLFactroy implements RouJiaMoYLFactroy
{

    @Override
    public Meat createMeat()
    {
        return new FreshMest();
    }

    @Override
    public YuanLiao createYuanliao()
    {
        return new XianTeSeYuanliao();
    }

}
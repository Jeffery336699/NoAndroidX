package com.circle.drawable.mode.state;

/**
 * 售罄的状态
 *
 * @author zhy
 *
 */
public class SoldOutState implements State
{

    private VendingMachineEx machine;

    public SoldOutState(VendingMachineEx machine)
    {
        this.machine = machine;
    }

    @Override
    public void insertMoney()
    {
        System.out.println("投币失败，商品已售罄");
    }

    @Override
    public void backMoney()
    {
        System.out.println("您未投币，想退钱么？...");
    }

    @Override
    public void turnCrank()
    {
        System.out.println("商品售罄，转动手柄也木有用");
    }

    @Override
    public void dispense()
    {
        throw new IllegalStateException("非法状态！");
    }

}

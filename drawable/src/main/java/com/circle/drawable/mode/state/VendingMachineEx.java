package com.circle.drawable.mode.state;

/**
 * 自动售货机
 * TODO: 状态机简直美妙,优秀!!
 * @author zhy
 */
public class VendingMachineEx {
    private State noMoneyState;
    private State hasMoneyState;
    private State soldState;
    private State soldOutState;
    private State winnerState;

    private int count = 0;
    private State currentState = noMoneyState;

    public VendingMachineEx(int count) {
        noMoneyState = new NoMoneyState(this);
        hasMoneyState = new HasMoneyState(this);
        soldState = new SoldState(this);
        soldOutState = new SoldOutState(this);
        winnerState = new WinnerState(this);

        if (count > 0) {
            this.count = count;
            currentState = noMoneyState;
        }
    }

    public void insertMoney() {
        currentState.insertMoney();
    }

    public void backMoney() {
        currentState.backMoney();
    }

    public void turnCrank() {
        currentState.turnCrank();
        if (currentState == soldState || currentState == winnerState)
            currentState.dispense();
    }

    public void dispense() {
        System.out.println("发出一件商品...");
        if (count != 0) {
            count -= 1;
        }
    }

    public void setState(State state) {
        this.currentState = state;
    }

    //getter setter omitted ...

    public State getNoMoneyState() {
        return noMoneyState;
    }

    public State getHasMoneyState() {
        return hasMoneyState;
    }

    public State getSoldState() {
        return soldState;
    }

    public State getSoldOutState() {
        return soldOutState;
    }

    public State getWinnerState() {
        return winnerState;
    }

    public int getCount() {
        return count;
    }
}

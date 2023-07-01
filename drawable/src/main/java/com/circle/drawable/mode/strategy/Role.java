package com.circle.drawable.mode.strategy;

import com.circle.drawable.inter.IAttackBehavior;
import com.circle.drawable.inter.IDefendBehavior;
import com.circle.drawable.inter.IDisplayBehavior;
import com.circle.drawable.inter.IRunBehavior;

/**
 * 游戏的角色超类
 *
 * @author zhy
 */
public abstract class Role {
    public String name;

    protected IDefendBehavior defendBehavior;
    protected IDisplayBehavior displayBehavior;
    protected IRunBehavior runBehavior;
    protected IAttackBehavior attackBehavior;

    public Role setDefendBehavior(IDefendBehavior defendBehavior) {
        this.defendBehavior = defendBehavior;
        return this;
    }

    public Role setDisplayBehavior(IDisplayBehavior displayBehavior) {
        this.displayBehavior = displayBehavior;
        return this;
    }

    public Role setRunBehavior(IRunBehavior runBehavior) {
        this.runBehavior = runBehavior;
        return this;
    }

    public Role setAttackBehavior(IAttackBehavior attackBehavior) {
        this.attackBehavior = attackBehavior;
        return this;
    }

    public void display() {
        displayBehavior.display();
    }

    public void run() {
        runBehavior.run();
    }

    public void attack() {
        attackBehavior.attack();
    }

    public void defend() {
        defendBehavior.defend();
    }

}
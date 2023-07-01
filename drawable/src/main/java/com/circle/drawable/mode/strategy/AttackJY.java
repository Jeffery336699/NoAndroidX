package com.circle.drawable.mode.strategy;

import com.circle.drawable.inter.IAttackBehavior;

public class AttackJY implements IAttackBehavior {
    @Override
    public void attack() {
        System.out.println("九阳神功！");
    }
}

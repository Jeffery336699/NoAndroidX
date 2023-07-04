package com.circle.drawable.mode.template;

import java.util.Date;

/**
 * 首先来个超类，超类中定义了一个workOneDay方法，设置为作为算法的骨架
 */
public abstract class Worker {
    protected String name;

    public Worker(String name) {
        this.name = name;
    }

    /**
     * 记录一天的工作[相当于模板]
     */
    public final void workOneDay() {

        System.out.println("-----------------work start ---------------");
        enterCompany();
        computerOn();
        work();
        computerOff();
        exitCompany();
        System.out.println("-----------------work end ---------------");

    }

    /**
     * 工作
     */
    public abstract void work();

    /**
     * 关闭电脑
     */
    private void computerOff() {
        System.out.println(name + "关闭电脑");
    }

    /**
     * 打开电脑
     */
    private void computerOn() {
        System.out.println(name + "打开电脑");
    }

    /**
     * 进入公司
     */
    public void enterCompany() {
        System.out.println(name + "进入公司");
    }

    public boolean isNeedPrintDate() {
        return false;
    }

    /**
     * 离开公司
     * TODO:模版方式里面也可以可选的设置钩子，比如现在希望记录程序员离开公司的时间
     */
    public void exitCompany() {
        if (isNeedPrintDate()) {
            System.out.print(new Date().toLocaleString() + "-->");
        }
        System.out.println(name + "离开公司");
    }

}
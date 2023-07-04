package com.circle.drawable.concur;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 改造后的门禁系统
 *
 * @author zhy
 */
public class CyclicBarrierTest2 {

    /**
     * 学生总数
     */
    private final int STUDENT_COUNT = 12;

    /**
     * 每3个人一组出门
     * TODO:这个例子充分的体现了CyclicBarrier的复用性
     */
    final CyclicBarrier barrier = new CyclicBarrier(3,
            new Runnable() {
                @Override
                public void run() {
                    System.out.println("有3个学生到齐了，放行....");
                }
            });

    public void goHome() throws InterruptedException, BrokenBarrierException {
        System.out.println(Thread.currentThread().getName() + "已刷卡，等待开门回家~");
        barrier.await();
    }

    public static void main(String[] args) {
        final CyclicBarrierTest2 instance = new CyclicBarrierTest2();

        /**
         * 每个线程代表一个学生
         */
        for (int i = 0; i < instance.STUDENT_COUNT; i++) {
            new Thread("学生" + i + "  ") {
                public void run() {
                    try {
                        instance.goHome();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }.start();
        }
    }
}
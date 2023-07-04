package com.circle.drawable.concur;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 安全的门禁系统
 *
 * @author zhy
 */
public class CyclicBarrierTest {

    /**
     * 学生总数
     */
    private final int STUDENT_COUNT = 10;

    /**
     * 当人到齐，自动开门程序
     */
    final CyclicBarrier barrier = new CyclicBarrier(STUDENT_COUNT,
            // TODO: 当数量达到这么多的时候才会执行如下Runnable方法
            new Runnable() {
                @Override
                public void run() {
                    System.out.println("人到齐了，开门....");
                }
            });

    // TODO:  barrier.await()会阻塞当前的线程,直到达到预设定的数量
    public void goHome() throws InterruptedException, BrokenBarrierException {
        System.out.println(Thread.currentThread().getName() + "已刷卡，等待开门回家~");
        barrier.await();
        System.out.println(Thread.currentThread().getName() + "放学回家~");
    }

    public static void main(String[] args) {

        final CyclicBarrierTest instance = new CyclicBarrierTest();

        /**
         * 每个线程代表一个学生
         */
        for (int i = 0; i < instance.STUDENT_COUNT; i++) {
            new Thread("学生" + i + "  ") {
                public void run() {
                    // TODO: 当前环境是在子线程中,阻塞就跟我预期相符[需要等到10个线程才放行]
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
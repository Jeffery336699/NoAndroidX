package com.circle.drawable.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.circle.drawable.R;
import com.circle.drawable.test.ScheduledThreadPoolDemo01;
import com.circle.drawable.test.ScheduledThreadPoolExecutorTest;
import com.circle.drawable.test.TimerTest;

/**
 * 博客参考鸿洋大神:
 * https://blog.csdn.net/lmj623565791/article/details/27109467?spm=1001.2014.3001.5502
 */
public class ScheduledExecutor2TimerMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduled_executor2_timer_main);
    }

    // TODO: 非真正的并行,前一个Task中休眠时间过长会影响到后面的task的执行开始时间
    public void onTimerDelay(View view)  {
        TimerTest.main(new String[]{});
    }

    // TODO: 多个任务真正并行执行,任务内休眠不会影响其他任务的开始执行时间
    public void onScheduledDelay(View view) {
        ScheduledThreadPoolExecutorTest.main(new String[]{});
    }

    // TODO: error任务对周期性任务有影响
    public void onTimerError(View view) {
        ScheduledThreadPoolDemo01.timerErrorRunTask();
    }

    // 无影响
    public void onScheduledError(View view) {
        ScheduledThreadPoolDemo01.scheduledExecutorServiceErrorRunTask();
    }
}
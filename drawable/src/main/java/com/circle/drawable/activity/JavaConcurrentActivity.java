package com.circle.drawable.activity;

import static com.circle.drawable.concur.Test1.fatherToRes;
import static com.circle.drawable.concur.Test1.meToRes;
import static com.circle.drawable.concur.Test1.motherToRes;
import static com.circle.drawable.concur.Test1.togetherToEat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.circle.drawable.R;
import com.circle.drawable.concur.BookInstance;
import com.circle.drawable.concur.ConnectPool;
import com.circle.drawable.concur.CyclicBarrierTest;
import com.circle.drawable.concur.CyclicBarrierTest2;
import com.circle.drawable.concur.MutexPrint;
import com.circle.drawable.concur.PreLoaderUseFutureTask;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;

public class JavaConcurrentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_concurrent);
    }

    // TODO: 当多个线程操作同一个变量时，为了保证变量修改对于其他线程的可见性，必须使用同步，volatile对于可见性的实现是个不错的选择
    //  但是我们代码中的 i -- 也有可能因为并发造成一定的问题，毕竟i--不是原子操作，正常最好使用同步块或者AtomicLong.decrementAndGet()实现--。
    private static volatile int i = 3;

    public void onCountDownLatch(View view) {
        new Thread() {
            public void run() {
                fatherToRes();
                i--;
            }
        }.start();
        new Thread() {
            public void run() {
                motherToRes();
                i--;
            }
        }.start();
        new Thread() {
            public void run() {
                meToRes();
                i--;
            }
        }.start();

        while (i != 0) ;
        togetherToEat();
    }

    // TODO: 避免使用忙等[就是上面主线程的while(i!=0)操作,当前线程是处于休眠状态,不占用CPU资源]，我们使用了CountDowmLatch  实现了我们的需求
    public void onCountDownLatchEx(View view) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);
        new Thread() {
            public void run() {
                fatherToRes();
                latch.countDown();
            }
        }.start();
        new Thread() {
            public void run() {
                motherToRes();
                latch.countDown();
            }
        }.start();
        new Thread() {
            public void run() {
                meToRes();
                latch.countDown();
            }
        }.start();

        latch.await();
        togetherToEat();
    }

    // TODO: Semaphore信号量作用：可以用来控制"同时访问"某个特定资源的操作"数量"，或者某个操作的数量。
    //  此例子中同时操作的数量为1,达到互斥的效果
    public void onSemaphore(View view) {
        MutexPrint.main(new String[]{});
    }

    // TODO: 此例中同时操作的数量为N,达到连接池容量为N的效果
    public void onSemaphoreEx(View view) {
        ConnectPool.main(new String[]{});
    }

    // TODO: 由于：FutureTask可以返回执行完毕的数据，并且FutureTask的get方法支持阻塞这两个特性，
    //  我们可以用来预先加载一些可能用到资源，然后要用的时候，调用get方法获取（如果资源加载完，直接返回；否则继续等待其加载完成）。
    public void onFutureTask(View view) throws ExecutionException, InterruptedException {
        PreLoaderUseFutureTask.main(new String[]{});
    }

    // TODO: 电子书每次预加载下一个页的内容
    public void onFutureTaskEx(View view) throws ExecutionException, InterruptedException {
        BookInstance.main(new String[]{});
    }

    public void onCyclicBarrier(View view) {
        CyclicBarrierTest.main(new String[]{});
    }

    public void onCyclicBarrierEx(View view) {
        CyclicBarrierTest2.main(new String[]{});
    }
}
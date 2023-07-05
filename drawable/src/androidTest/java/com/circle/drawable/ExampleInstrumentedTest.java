package com.circle.drawable;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        double dy = 0;
        double dx = 10;
        double atan2 = Math.atan2(dy, dx);
        int degrees = (int) Math.toDegrees(atan2);
        System.out.println("atan2="+atan2);
        System.out.println("degrees="+degrees);
    }
}
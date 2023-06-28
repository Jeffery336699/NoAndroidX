package com.circle.drawable;

import static android.content.ContentValues.TAG;

import org.junit.Test;

import static org.junit.Assert.*;

import android.util.Log;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    public static final String TAG = "ExampleUnitTest";

    @Test
    public void addition_isCorrect() {
        float val = 1;
        float s = 0.85f;
        int i = 0;
        s = (float) Math.sqrt(1 / s);
        System.out.println(val);
        while (i < 5) {
            val = val * s;
            System.out.println(val);
            i++;
        }
        s = 0.85f;
        i = 0;
        s = (float) Math.sqrt(s);
        while (i < 5) {
            val = val * s;
            System.out.println(val);
            i++;
        }
    }
}
package com.circle.drawable.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.circle.drawable.R;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import java.util.Arrays;

public class PinYinDemoActivity extends AppCompatActivity {

    private TextView mTvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin_yin_demo);
        mTvResult = findViewById(R.id.tvResult);
    }

    public void onPinYin(View view) throws BadHanyuPinyinOutputFormatCombination {
        //指定Pinyin格式转换
        String str = "单赵钱孙李周吴郑王冯陈褚卫abc";
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        format.setToneType(HanyuPinyinToneType.WITH_TONE_NUMBER);

        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            String[] vals = PinyinHelper.toHanyuPinyinStringArray(c, format);
            System.out.print(Arrays.toString(vals));
            sb.append(Arrays.toString(vals)).append("\n");
        }
        mTvResult.setText(sb.toString());

    }
}
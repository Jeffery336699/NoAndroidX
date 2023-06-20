package com.example.noandroidx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MeasureActivity extends AppCompatActivity {

    private static final String TAG = "MeasureActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_measure);
        TextView tvMs = findViewById(R.id.tvMs);
        tvMs.setText("xjijearfjfasifjijfjfjiaiiii");
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec((1 << 30) - 1, View.MeasureSpec.AT_MOST);
        Log.i(TAG, "onCreate: "+((1 << 30) - 1));
        tvMs.measure(makeMeasureSpec, makeMeasureSpec);
        Log.i(TAG, "onCreate: ==>"+tvMs.getMeasuredWidth());
        Log.i(TAG, "onCreate: ==>"+tvMs.getMeasuredHeight());

        findViewById(R.id.tv3).setEnabled(false);
    }

    public void onRL(View view) {
        Toast.makeText(this, "RL--", Toast.LENGTH_SHORT).show();
        if (view instanceof ViewGroup){
            ViewGroup viewGroup = (ViewGroup) view;
            TextView last = (TextView) viewGroup.getChildAt(viewGroup.getChildCount() - 1);

            Toast.makeText(this, last.getText(), Toast.LENGTH_SHORT).show();
        }
    }

    public void onBt1(View view) {
        Toast.makeText(this, "BTTTT--11", Toast.LENGTH_SHORT).show();
    }
     public void onBt2(View view) {
        Toast.makeText(this, "BTTTT--222", Toast.LENGTH_SHORT).show();
    }
     public void onBt3(View view) {
        Toast.makeText(this, "BTTTT--333", Toast.LENGTH_SHORT).show();
    }


}
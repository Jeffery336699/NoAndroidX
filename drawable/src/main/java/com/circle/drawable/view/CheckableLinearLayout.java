package com.circle.drawable.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Checkable;
import android.widget.LinearLayout;

import com.circle.drawable.R;
//TODO 参考：http://chanthuang.github.io/2016/08/15/%E4%BB%8E-Android-%E6%BA%90%E7%A0%81%E8%A7%92%E5%BA%A6%E5%88%86%E6%9E%90-View-%E7%9A%84%E7%8A%B6%E6%80%81%E6%94%B9%E5%8F%98%E5%A6%82%E4%BD%95%E5%BD%B1%E5%93%8D-Drawable-%E7%9A%84%E8%A1%A8%E7%8E%B0/
public class CheckableLinearLayout extends LinearLayout implements Checkable {

    private boolean mIsChecked = false;

    private boolean mIsEditing = false;

    private Drawable mCheckboxDrawable;

    private static final int[] CHECKED_STATE_SET = {
            android.R.attr.state_checked
    };

    public CheckableLinearLayout(Context context) {
        super(context);
        init();
    }

    public CheckableLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CheckableLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // TODO: 这个selector里面就包含state_checked与normal时的状态
        mCheckboxDrawable = getResources().getDrawable(R.drawable.dialog_check_mark);
        // 恢复 ViewGroup 的 draw 功能（默认关闭），使 onDraw 方法会被调用
        setWillNotDraw(false);
//        setBackgroundColor(0xAABB86FC);
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        // 将 getDrawableState 返回的状态数组设置给 mCheckboxDrawable，并触发重绘
        if (mCheckboxDrawable != null) {
            int[] drawableState = getDrawableState();
            mCheckboxDrawable.setState(drawableState);
            invalidate();
        }
    }

    @Override
    protected int[] onCreateDrawableState(int extraSpace) {
        // 调用 super 时参数加上状态集的长度
        final int[] drawableState = super.onCreateDrawableState(extraSpace + CHECKED_STATE_SET.length);
        if (isChecked()) {
            // 被 checked 状态下，在 super 返回的数组上追加自己的状态集合
            mergeDrawableStates(drawableState, CHECKED_STATE_SET);
        }
        return drawableState;
    }

    @Override
    public void setChecked(boolean checked) {
        if (mIsChecked != checked) {
            mIsChecked = checked;
            // checked 状态改变时调用 refreshDrawableState()
            refreshDrawableState();
        }
    }

    @Override
    public boolean isChecked() {
        return mIsChecked;
    }

    @Override
    public void toggle() {
        setChecked(!isChecked());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 将 mCheckboxDrawable 画到 Canvas 上
        if (isEditing() && mCheckboxDrawable != null) {
            int left = dpToPx(5);
            mCheckboxDrawable.setBounds(left, getPaddingTop(),
                    left + mCheckboxDrawable.getIntrinsicWidth(),
                    getPaddingTop() + mCheckboxDrawable.getIntrinsicHeight());
            mCheckboxDrawable.draw(canvas);
        }
    }

    public void setEditable(boolean editable) {

    }

    public boolean isEditable() {
        return true;
    }

    public void setEdit(boolean edit) {
        if (mIsEditing != edit) {
            mIsEditing = edit;
            updatePaddingForDrawable(mIsEditing);
        }
    }

    public void toggleEditMode() {
        setEdit(!isEditing());
    }

    public boolean isEditing() {
        return mIsEditing;
    }

    private void updatePaddingForDrawable(boolean isEditing) {
        setPadding(
                isEditing ?
                        getPaddingLeft() + mCheckboxDrawable.getIntrinsicWidth() + dpToPx(10) :
                        getPaddingLeft() - mCheckboxDrawable.getIntrinsicWidth() - dpToPx(10),
                getPaddingTop(), getPaddingRight(), getPaddingBottom()
        );
    }

    private int dpToPx(int dpValue) {
        return (int) (dpValue * Resources.getSystem().getDisplayMetrics().density + 0.5f);
    }
}
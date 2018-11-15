package com.feiyang.wanandroid;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/10/24 5:00 PM<br>
 * Desc: 展示进度的控件<br>
 */
public class StepBar extends LinearLayout {
    private @ColorInt
    int mNormalColor, mSelectedColor;

    private List<StepBean> mData = new ArrayList<>();

    private String[] titles = {"已签到", "已核货", "待配送", "待缴款"};

    public int getNormalColor() {
        return mNormalColor;
    }

    public void setNormalColor(int mNormalColor) {
        this.mNormalColor = mNormalColor;
        invalidate();
    }

    public int getSelectedColor() {
        return mSelectedColor;
    }

    public void setSelectedColor(int mSelectedColor) {
        this.mSelectedColor = mSelectedColor;
        invalidate();
    }

    public List<StepBean> getData() {
        return mData;
    }

    public void setData(@NonNull List<StepBean> mTitles) {
        this.mData = mTitles;
        invalidate();
    }

    public StepBar(Context context) {
        super(context);
        initAttr(context, null, 0);
    }

    public StepBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttr(context, attrs, 0);
    }

    public StepBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttr(context, attrs, defStyleAttr);
    }

    private void initAttr(Context context, AttributeSet attrs, int defStyleAttr) {
        setOrientation(HORIZONTAL);
        setGravity(Gravity.CENTER_VERTICAL);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.StepBar, defStyleAttr, 0);
        try {
            mNormalColor = ta.getColor(R.styleable.StepBar_normalColor, Color.GRAY);
            mSelectedColor = ta.getColor(R.styleable.StepBar_selectedColor, Color.BLUE);
            for (int i = 0; i < 4; i++) {
                StepBean stepBean = new StepBean();
                stepBean.setTitle(titles[i]);
                stepBean.setSelected(i == 2);
                stepBean.setSnail(i == 3);
                mData.add(stepBean);
            }
            if (mData != null && mData.size() > 0) {
                for (StepBean stepBean : mData) {
                    TextView tv = new TextView(context);
                    tv.setTextColor(Color.WHITE);
                    tv.setText(stepBean.getTitle());
                    tv.setTextSize(15);
                    tv.setGravity(Gravity.CENTER);
                    LayoutParams params  = new LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT, 1);
                    int          padding = 6;
                    tv.setPadding(padding, padding, padding, padding);
                    tv.setLayoutParams(params);
                    Drawable background;
                    if (stepBean.isSelected()) {
                        background = getResources().getDrawable(R.drawable.bg_selected);
                    } else {
                        background = getResources().getDrawable(R.drawable.bg_unselected);
                    }
                    tv.setBackground(background);
                    addView(tv);
                    if (!stepBean.isSnail()) {
                        TextView tvArrow = new TextView(context);
                        tvArrow.setText(">>");
                        tvArrow.setTextColor(Color.parseColor("#444444"));
                        addView(tvArrow);
                    }
                }
            }
            requestLayout();
            invalidate();
        } finally {
            ta.recycle();
        }
    }


}

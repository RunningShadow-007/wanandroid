package com.feiyang.wanandroid.core.widget;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2019/1/14 7:53 PM<br>
 * Desc: <br>
 */
public class MarqueeTextView extends AppCompatTextView {

    public MarqueeTextView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        setSingleLine();
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
    }

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public MarqueeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }


    @Override
    public boolean isFocused() {
        return true;
    }
}

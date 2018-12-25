package com.feiyang.wanandroid.core.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;

import com.feiyang.wanandroid.ui.main.adpter.BannerAdapter;
import com.feiyang.wanandroid.ui.main.model.bean.BannerData;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/13 2:54 PM<br>
 * Desc: <br>
 */
public class Banner extends ViewPager {
    private int mSelectedIndex = 0;

    private List<BannerData> mData = new ArrayList<>();

    private Handler mHandler;

    private OnPageChangedCallback onPageChangedCallback;

    public Banner(@NonNull Context context) {
        this(context,null);

    }

    public Banner(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void play(@NonNull List<BannerData> data) {
        if (data.size() > 0) {
            this.mData = data;
            if (mHandler == null) {
                mHandler = new Handler(Looper.getMainLooper());
                BannerAdapter adapter = new BannerAdapter(mData);
                setAdapter(adapter);
                addOnPageChangeListener(mOnPageChangeListener);
                setCurrentItem(getInitPosition());
                if (mData.size() > 1) {
                    startPlay();
                }
            }
        }
    }

    private OnPageChangeListener mOnPageChangeListener = new OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            Log.e("Banner", "onPageScrolled: " + position);
            if (mSelectedIndex != position) {
                if (onPageChangedCallback != null) {
                    onPageChangedCallback.onPageChanged(String.format("%s/%s", (mSelectedIndex % mData.size()) + 1, mData.size()), getItem().getTitle());
                }
            }
            mSelectedIndex = position;
        }

        @Override
        public void onPageSelected(int position) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {
            if (state == ViewPager.SCROLL_STATE_IDLE) {
                startPlay();
            }
        }
    };


    private Runnable mAutoPlayTask = () -> {
        if (mSelectedIndex == Integer.MAX_VALUE) {
            int rightPos = mSelectedIndex % mData.size();
            setCurrentItem(getInitPosition() + rightPos + 1, true);
        } else {
            setCurrentItem(mSelectedIndex + 1, true);
        }
    };

    public BannerData getItem() {
        int pos = mSelectedIndex % mData.size();
        return mData.get(pos);
    }

    /**
     * 获取banner的初始位置,即0-Integer.MAX_VALUE之间的大概中间位置
     * 保证初始位置和数据源的第1个元素的取余为0
     *
     * @return
     */
    private int getInitPosition() {
        if (mData.isEmpty()) {
            return 0;
        }
        int middlePos = Integer.MAX_VALUE / 2;
        int pos       = middlePos % mData.size();
        return middlePos - pos;
    }


    private void startPlay() {
        mHandler.removeCallbacks(mAutoPlayTask);
        mHandler.postDelayed(mAutoPlayTask, 2500);
    }

    public interface OnPageChangedCallback {
        void onPageChanged(String indexTxt, String title);
    }

    public void setOnPageChangedCallback(OnPageChangedCallback onPageChangedCallback) {
        this.onPageChangedCallback = onPageChangedCallback;
    }
}

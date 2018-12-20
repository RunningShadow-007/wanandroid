package com.feiyang.wanandroid.ui.main.adpter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.feiyang.wanandroid.ui.main.model.bean.BannerData;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/13 2:57 PM<br>
 * Desc: <br>
 */
public class BannerAdapter extends PagerAdapter {
    private List<BannerData> mData;

    private List<Object> mViewCaches = new ArrayList<>();

    public BannerAdapter(@NonNull List<BannerData> mData) {
        this.mData = mData;
    }

    @Override
    public int getCount() {
        if (mData != null && mData.size() > 0) {
            if (mData.size() == 1) {
                return 1;
            } else {
                return Integer.MAX_VALUE;
            }
        } else {
            return 0;
        }
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ImageView pic;
        if (mViewCaches.isEmpty()) {
            pic = new ImageView(container.getContext());
            pic.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            pic.setScaleType(ImageView.ScaleType.FIT_XY);
        } else {
            pic = (ImageView) mViewCaches.remove(0);
        }
        Glide.with(container.getContext()).load(getItem(position).getImagePath()).into(pic);
        container.addView(pic);
        return pic;
    }

    private BannerData getItem(int position) {
        return mData.get(position % mData.size());
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ImageView imageView= (ImageView) object;
        container.removeView(imageView);
        mViewCaches.add(imageView);
    }
}

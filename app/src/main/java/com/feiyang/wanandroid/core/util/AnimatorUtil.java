package com.feiyang.wanandroid.core.util;

import android.view.View;
import android.view.animation.AccelerateInterpolator;

import androidx.core.view.ViewCompat;
import androidx.core.view.ViewPropertyAnimatorListener;
import androidx.interpolator.view.animation.LinearOutSlowInInterpolator;

/**
 * Copyright:wanandroid2
 * Author: liyang <br>
 * Date:2018/12/12 5:47 PM<br>
 * Desc: <br>
 */
public class AnimatorUtil {

    private static LinearOutSlowInInterpolator FAST_OUT_SLOW_IN_INTERPOLATOR = new LinearOutSlowInInterpolator();

    private static AccelerateInterpolator LINER_INTERPOLATOR = new AccelerateInterpolator();


    /**
     * 显示view
     *
     * @param view View
     * @param viewPropertyAnimatorListener ViewPropertyAnimatorListener
     */
    public static void scaleShow(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        view.setVisibility(View.VISIBLE);
        ViewCompat.animate(view)
                  .scaleX(1.0f)
                  .scaleY(1.0f)
                  .alpha(1.0f)
                  .setDuration(800)
                  .setListener(viewPropertyAnimatorListener)
                  .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                  .start();
    }

    /**
     * 隐藏view
     *
     * @param view View
     * @param viewPropertyAnimatorListener ViewPropertyAnimatorListener
     */
    public static void scaleHide(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        ViewCompat.animate(view)
                  .scaleX(0.0f)
                  .scaleY(0.0f)
                  .alpha(0.0f)
                  .setDuration(800)
                  .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                  .setListener(viewPropertyAnimatorListener)
                  .start();
    }

    /**
     * 显示view
     *
     * @param view View
     * @param viewPropertyAnimatorListener ViewPropertyAnimatorListener
     */
    public static void translateShow(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        view.setVisibility(View.VISIBLE);
        ViewCompat.animate(view)
                  .translationY(0)
                  .setDuration(400)
                  .setListener(viewPropertyAnimatorListener)
                  .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                  .start();
    }

    /**
     * 隐藏view
     *
     * @param view View
     * @param viewPropertyAnimatorListener ViewPropertyAnimatorListener
     */
    public static void translateHide(View view, ViewPropertyAnimatorListener viewPropertyAnimatorListener) {
        view.setVisibility(View.VISIBLE);
        ViewCompat.animate(view)
                  .translationY(350)
                  .setDuration(400)
                  .setInterpolator(FAST_OUT_SLOW_IN_INTERPOLATOR)
                  .setListener(viewPropertyAnimatorListener)
                  .start();
    }
}

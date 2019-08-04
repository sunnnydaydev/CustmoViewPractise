package com.sunnyday.administrator.customviewpractise.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Create by SunnyDay on 2019/08/04
 *
 * 贝塞尔曲线--波浪线特效
 */
public class WaveView extends View {
    public WaveView(Context context) {
        this(context,null);
    }

    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}

package com.sunnyday.administrator.customviewpractise.customviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by sunnyDay on 2019/8/8 17:13
 * <p>
 * 小米秒表：待续参考：https://github.com/chenzongwen/MiClockView
 */
public class MiStopWatch extends View {
    public MiStopWatch(Context context) {
        this(context,null);
    }

    public MiStopWatch(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MiStopWatch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }
}

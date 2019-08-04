package com.sunnyday.administrator.customviewpractise.customviews;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Create by SunnyDay on 2019/08/04
 *
 * 贝塞尔曲线--波浪线特效
 */
public class WaveView extends View {
    private int width;
    private  int height;
    private int baseLine;

    private  int waveHeight = 80;
    private  int waveWidth; // 波长

    private float offset;
    private Paint paint;

    public WaveView(Context context) {
        this(context,null);
    }

    public WaveView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public WaveView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    /**
     * 初始化画笔
     * */
    private void initPaint() {
        paint = new Paint();
        paint.setColor(0xff69e0f2);
        paint.setStyle(Paint.Style.FILL);
        paint.setStrokeWidth(10);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width =w;
        height = h;
        waveWidth = width;
        baseLine = height/2;

        // 播放动画
        ValueAnimator mAnimator = ValueAnimator.ofFloat(0,waveWidth);
        mAnimator.setInterpolator(new LinearInterpolator());
        mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                offset = (float)animation.getAnimatedValue();
                postInvalidate();
            }
        });
        mAnimator.setDuration(1500);
        mAnimator.setRepeatCount(ValueAnimator.INFINITE); //无限循环
        mAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int itemWidth = waveWidth /2; //// 半个波长（AC长度）

        Path path = new Path();
        path.moveTo(-itemWidth*3,baseLine);
        // 贝塞尔曲线
        for (int i = -3; i < 2; i++) {
          int startX = i* itemWidth;
          path.quadTo(startX+itemWidth/2+offset,getWaveHeight(i),
                      startX+itemWidth+offset,baseLine);
        }
        // 密封空间
        path.lineTo(width,height);
        path.lineTo(0,height);
        path.close();

        canvas.drawPath(path,paint);
    }

    private int getWaveHeight(int number){
        if (number%2==0){
            return baseLine+waveHeight;
        }else{
            return baseLine-waveHeight;
        }
    }
}

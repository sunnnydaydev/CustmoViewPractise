package com.sunnyday.administrator.customviewpractise.customviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.FocusFinder;
import android.view.View;
import com.sunnyday.administrator.customviewpractise.R;
import com.sunnyday.administrator.customviewpractise.utils.DensityUtils;

import java.util.Calendar;

/**
 * Created by sunnyDay on 2019/8/8 17:13
 * <p>
 * 小米秒表(先手打一遍，在研究)：项目来源：https://github.com/chenzongwen/MiClockView
 */
public class MiStopWatch extends View {
    private static final String TAG = "23333";
    private Context mContext;
    private int mLightColor;
    private int mDarkColor;
    private Paint mTextPaint;
    private float mCircleStrokeWidth = 4;
    private Paint mCirclePaint;
    private Paint mScaleLinePaint;
    private Paint mScaleArcPaint;
    private Matrix mGradientMatrix;
    private float mRadius;
    private float mPaddingLeft;
    private float mPaddngRight;
    private float mPaddingTop;
    private float mPaddingBottom;
    private float mScaleLength;
    private SweepGradient mSweepGradient;
    private Canvas mCanvas;
    private float mSecondDegree;
    private float mMinuteDegree;
    private float mHourDegree;
    private Rect mTextRect = new Rect();
    RectF mCircleRectF = new RectF();

    public MiStopWatch(Context context) {
        this(context, null);
    }

    public MiStopWatch(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MiStopWatch(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.MiStopWatch);
        int mBackgroudColor = ta.getColor(R.styleable.MiStopWatch_color_background_color, Color.parseColor("#237EAD"));
        mLightColor = ta.getColor(R.styleable.MiStopWatch_color_light_color, Color.parseColor("#ffffff"));//白色全透明
        mDarkColor = ta.getColor(R.styleable.MiStopWatch_color_dark_color, Color.parseColor("#80ffffff"));//白色透明80（取值范围0-255）

        // 小时12-3-6-9的字号大小
        int mTextSize = (int) ta.getDimension(R.styleable.MiStopWatch_clock_textSize, DensityUtils.sp2px(context, 14));
        ta.recycle();

        // 12-3-6-9小时画笔 的初始化
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);//抗锯齿
        mTextPaint.setColor(mDarkColor);//画笔颜色
        mTextPaint.setStyle(Paint.Style.FILL);// 填充
        mTextPaint.setTextAlign(Paint.Align.CENTER);//文字居中
        mTextPaint.setTextSize(mTextSize);//文字大小

        // 小圆圈画笔
        mCirclePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCirclePaint.setStyle(Paint.Style.STROKE);
        mCirclePaint.setStrokeWidth(mCircleStrokeWidth);
        mCirclePaint.setColor(mDarkColor);

        //刻度线画笔
        mScaleLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mScaleLinePaint.setColor(mBackgroudColor);
        mScaleLinePaint.setStyle(Paint.Style.STROKE);

        // 刻度圆弧画笔
        mScaleArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mScaleArcPaint.setStyle(Paint.Style.STROKE);

        // 渐变矩阵
        mGradientMatrix = new Matrix();

        // 秒指针画笔
        Paint mSecondHandPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSecondHandPaint.setStyle(Paint.Style.FILL);
        mSecondHandPaint.setColor(mLightColor);

        // 分钟指针画笔
        Paint mMinuteHandPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mMinuteHandPaint.setStyle(Paint.Style.FILL);
        mMinuteHandPaint.setColor(mLightColor);

        // 小时指针画笔
        Paint mHourHandPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mHourHandPaint.setStyle(Paint.Style.FILL);
        mHourHandPaint.setColor(mDarkColor);
    }

    /**
     * wrap_content的处理
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureDimension(widthMeasureSpec), measureDimension(heightMeasureSpec));
    }

    private int measureDimension(int measureSpec) {
        int defaultSize = 800;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);
        switch (mode) {
            case MeasureSpec.EXACTLY:
                return size;
            case MeasureSpec.AT_MOST:
                return Math.min(size, defaultSize);//处理view的wrap_content 属性
            case MeasureSpec.UNSPECIFIED:
            default:
                return defaultSize;
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        // 钟表半径
        mRadius = Math.min(w - getPaddingLeft() - getPaddingRight(), h - getPaddingTop() - getPaddingBottom()) / 2;
        float mDefaultPadding = 0.12f * mRadius;

        // 给view添加 padding
        mPaddingLeft = mDefaultPadding + w / 2 - mRadius + getPaddingLeft();
        mPaddngRight = mDefaultPadding + w / 2 - mRadius + getPaddingRight();
        mPaddingTop = mDefaultPadding + h / 2 - mRadius + getPaddingTop();
        mPaddingBottom = mDefaultPadding + h / 2 - mRadius + getPaddingBottom();


        mScaleLength = 0.12f * mRadius;//刻度线的长度
        mScaleLinePaint.setStrokeWidth(0.012f * mRadius);//刻度线的宽度

        mScaleArcPaint.setStrokeWidth(mScaleLength); // 圆弧的线宽

        //SweepGradient 颜色渐变
        //参数：中心点、颜色、起始颜色所占比例（第一种颜色占75%，第二种颜色占25%（1-75%））
        mSweepGradient = new SweepGradient(w / 2, h / 2, new int[]{mDarkColor, mLightColor}, new float[]{0.75f, 1});

    }

    @Override
    protected void onDraw(Canvas canvas) {
        mCanvas = canvas;
        mCanvas.drawColor(mContext.getResources().getColor(R.color.orange));
        getCurrentTime();
        drawOutsideArc();
    }

    /**
     * 画最外层的时间（文本内容：12-3-6-9）及外层四段圆弧
     */
    private void drawOutsideArc() {
        String[] timeList = new String[]{"12", "3", "6", "9"};
        mTextPaint.getTextBounds(timeList[1], 0, timeList[1].length(), mTextRect);
        mCircleRectF.set(mPaddingLeft + mTextRect.width() / 2 + mCircleStrokeWidth / 2,
                mPaddingTop + mTextRect.height() / 2 + mCircleStrokeWidth / 2,
                getWidth() - mPaddngRight - mTextRect.width() / 2 - mCircleStrokeWidth / 2,
                getHeight() - mPaddingBottom - mTextRect.height() / 2 - mCircleStrokeWidth / 2);

        mCanvas.drawText(timeList[0], getWidth() / 2, mCircleRectF.top + mTextRect.height() / 2, mTextPaint);// 12
        mCanvas.drawText(timeList[1], mCircleRectF.right, getHeight() / 2 + mTextRect.height() / 2, mTextPaint);// 3
        mCanvas.drawText(timeList[2], getWidth() / 2, mCircleRectF.bottom + mTextRect.height() / 2, mTextPaint);// 6
        mCanvas.drawText(timeList[3], mCircleRectF.left, getHeight() / 2 + mTextRect.height() / 2, mTextPaint);// 9

        // 画四条圆弧
        for (int i = 0; i < 4; i++) {
            mCanvas.drawArc(mCircleRectF,5+90*i,80,false,mCirclePaint);
        }
    }

    /**
     * 获得当前时间
     */
    private void getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        float milliSecond = calendar.get(Calendar.MILLISECOND);//毫秒
        float second = calendar.get(Calendar.SECOND) + milliSecond / 1000;
        float minute = calendar.get(Calendar.MINUTE) + second / 60;
        float hour = calendar.get(Calendar.HOUR) + minute / 60;

        mSecondDegree = second / 60 * 360;//秒针角度
        mMinuteDegree = minute / 60 * 360;  //分针角度
        mHourDegree = hour / 12 * 360; //时针角度

    }
}

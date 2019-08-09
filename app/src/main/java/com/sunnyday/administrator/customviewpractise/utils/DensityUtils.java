package com.sunnyday.administrator.customviewpractise.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by sunnyDay on 2019/8/8 19:49
 * 尺寸转换工具
 * 公式：px = dp*density
 * 详情参考：https://blog.csdn.net/qq_38350635/article/details/98884486
 */
public class DensityUtils {
    /**
     * dp转px（使用公式）
     * 小数为了四舍五入
     */
    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px转dp（使用公式）
     *  小数为了四舍五入
     */
    public static float px2dp(Context context, float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale+0.5f);
    }

    /**
     * sp转px（使用系统推荐的工具）
     */
    public static int sp2px(Context context, float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * px转sp（使用系统推荐的工具）
     */
    public static float px2sp(Context context, float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }
}

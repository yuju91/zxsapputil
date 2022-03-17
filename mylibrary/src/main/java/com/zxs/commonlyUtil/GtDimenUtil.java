package com.zxs.commonlyUtil;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.Surface;
import android.view.WindowManager;

import java.lang.reflect.Field;

/**
 * 系统工具
 *
 * @author Bob Ackles
 */
public class GtDimenUtil {
    
    public static final int DEFAULT_TOOLBAR_HEIGHT_IN_DP = 56;
    
    public static int[] getScreenSize(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return new int[]{metrics.widthPixels, metrics.heightPixels};
    }
    
    public static int getScreenWidth(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.widthPixels;
    }
    
    public static int getScreenHeight(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.heightPixels;
    }
    
    public static float getScreenDensity(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.density;
    }
    
    public static int getScreenDensityDpi(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.densityDpi;
    }
    
    public static float getScreenScaledDensity(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return metrics.scaledDensity;
    }
    
    public static int dp2px(Context context, float dp) {
        return (int) (dp * getScreenDensity(context));
    }
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
    public static int sp2px(Context context, float sp) {
        return (int) (sp * getScreenScaledDensity(context));
    }
    // 将px值转换为dip或dp值
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
    // 将px值转换为sp值
    public static int px2sp(Context context, float pxValue) {
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (pxValue / fontScale + 0.5f);
    }
    public static boolean isPortrait(Context context) {
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int rotation = manager.getDefaultDisplay().getRotation();
        return rotation == Surface.ROTATION_90 || rotation == Surface.ROTATION_270;
    }
    
    /**
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        int statusBarHeight = 0;
        // 方法一
        try {
            int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
            if (resourceId > 0) {
                statusBarHeight = context.getResources().getDimensionPixelSize(resourceId);
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        // 方法二
        if (statusBarHeight <= 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object obj = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = Integer.parseInt(field.get(obj).toString());
                statusBarHeight = context.getResources().getDimensionPixelSize(x);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        // 方法三
        if (statusBarHeight <= 0) {
            statusBarHeight = dp2px(context, 25F);
        }
        return statusBarHeight;
    }
    
    /**
     * @return 导航栏高度
     */
    public static int getNavigationBarHeight(Context context) {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("navigation_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = context.getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }
    

    
    public static void log(Context context) {
        System.out.println("------------------------------------");
        System.out.println("screen width = " + getScreenWidth(context)
                + ", height = " + getScreenHeight(context));
        System.out.println("status bar height = " + getStatusBarHeight(context));
        System.out.println("navigation bar height = " + getNavigationBarHeight(context));
        System.out.println("density = " + getScreenDensity(context));
        System.out.println("density dpi = " + getScreenDensityDpi(context));
        System.out.println("------------------------------------");
    }
}

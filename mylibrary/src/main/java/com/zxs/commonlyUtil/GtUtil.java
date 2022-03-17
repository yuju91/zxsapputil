package com.zxs.commonlyUtil;

import android.app.Application;

/**
 * Created by zxs
 * on 2022/3/17
 */
public class GtUtil extends Application {
    //SingleInstance.class
    private volatile static GtUtil mSingleInstance = null;
    public GtACache gtACache;
    public GtValid gtValid;

    /*单例*/
    public static GtUtil getInstance() {
        if (mSingleInstance == null) {
            synchronized (GtUtil.class) {
                if (mSingleInstance == null) {
                    mSingleInstance = new GtUtil();
                }
            }
        }
        return mSingleInstance;

    }

    @Override
    public void onCreate() {
        super.onCreate();
        gtACache = GtACache.get(this);
    }
}

package com.love.rain;

import android.app.Application;

import com.love.rain.util.LogUtils;

/**
 * 作者    Chin_style
 * 时间    2018/12/1
 * 文件    Rainman
 * 描述
 * 致谢    Thank you for your advice.
 */
public class RainApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.LOG_DEBUG) {
            LogUtils.isShowLog = true;
        } else {
            LogUtils.isShowLog = false;
        }
    }
}
/*public class RainApplication extends CustomApplication {
    private static RainApplication mInstance;
    private static RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    public static RainApplication getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}*/

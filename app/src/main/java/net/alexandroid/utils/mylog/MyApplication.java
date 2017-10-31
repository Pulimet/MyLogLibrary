package net.alexandroid.utils.mylog;

import android.app.Application;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        MyLog.init(this);
        MyLog.showLogs(BuildConfig.DEBUG);
        MyLog.d("init");
    }
}

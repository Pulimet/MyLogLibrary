package net.alexandroid.utils.mylogsmaple;

import android.app.Application;

import net.alexandroid.utils.mylog.MyLog;
import net.alexandroid.utils.mylogkt.MyLogKt;

import static net.alexandroid.utils.mylogkt.MyLogKtKt.logD;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Java version
        MyLog.init(this, "MyLog", BuildConfig.DEBUG);
        MyLog.d("init");

        // Kotlin version
        MyLogKt.INSTANCE.setPackageName(getPackageName());
        logD("init");
    }
}

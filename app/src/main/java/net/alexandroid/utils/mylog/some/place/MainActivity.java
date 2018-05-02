package net.alexandroid.utils.mylog.some.place;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import net.alexandroid.utils.mylog.MyLog;
import net.alexandroid.utils.mylog.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
    }

    private void setViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                log();
            }
        });
    }


    private void log() {
        justPrintLogs();
        showPackageName();
        hideTime();
        showOverride();
        showThreadId();
        printExceptionLog();
    }

    private void printExceptionLog() {
        MyLog.e("Show Exception", new NullPointerException());
        MyLog.d("===============================================================");
    }

    private void showThreadId() {
        MyLog.setThreadIdVisibility(true);
        MyLog.w("Show thread is");
        MyLog.w("enabled");
        MyLog.d("===============================================================");
    }

    private void showOverride() {
        MyLog.w("Remove override disabled");
        MyLog.setIsRemoveOverride(false);
        MyLog.d("===============================================================");
    }

    private void hideTime() {
        MyLog.setIsTimeVisible(false);
        MyLog.w("Show time disabled");
        MyLog.d("Some log for tests");
        MyLog.d("===============================================================");
    }

    private void showPackageName() {
        MyLog.setPackageNameVisibility(true);
        MyLog.w("Show package enabled");
        MyLog.d("Some log for tests");
        MyLog.d("===============================================================");
    }

    private void justPrintLogs() {
        MyLog.d("Empty 1");
        MyLog.i("Empty 2");
        MyLog.w("Empty 3");
        MyLog.e("Empty 4");
        MyLog.d("===============================================================");
    }
}

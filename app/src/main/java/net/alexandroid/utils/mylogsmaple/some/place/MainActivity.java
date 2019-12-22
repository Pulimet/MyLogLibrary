package net.alexandroid.utils.mylogsmaple.some.place;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.alexandroid.utils.mylog.MyLog;
import net.alexandroid.utils.mylogsmaple.R;

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

        findViewById(R.id.btnOpenKotlin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, KotlinActivity.class));
            }
        });
    }

    private void log() {
        justPrintLogs();
        customTag();
        showPackageName();
        hideTime();
        showThreadId();
        printExceptionLog();
    }

    private void printExceptionLog() {
        MyLog.e("Show Exception", new NullPointerException());
        MyLog.d("===============================================================");
    }

    private void customTag() {
        MyLog.d("CustomTag1", "Custom tag example 1");
        MyLog.d("CustomTag2", "Custom tag example 2");
    }

    private void showThreadId() {
        MyLog.setThreadIdVisibility(true);
        MyLog.w("Show thread is");
        MyLog.w("enabled");
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

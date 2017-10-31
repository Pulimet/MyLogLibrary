package net.alexandroid.utils.mylog.some.place;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import net.alexandroid.utils.mylog.MyLog;
import net.alexandroid.utils.mylog.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();


        Log.d("YOUR_TAG", "Simple log example");

        MyLog.d("===============================================================");
        MyLog.e("Empty 1");
        MyLog.e("Empty 2");
        MyLog.d("===============================================================");

        MyLog.setTag("NEW TAG");
        MyLog.i("Tag changed");
        MyLog.i("to NEW TAG");
        MyLog.d("===============================================================");

        MyLog.setPackageNameVisibility(true);
        MyLog.w("Show package");
        MyLog.w("enabled");
        MyLog.d("===============================================================");

        MyLog.setIsTimeVisible(false);
        MyLog.w("Show time");
        MyLog.w("enabled");
        MyLog.d("===============================================================");

        MyLog.setIsRemoveOverride(false);
        MyLog.w("Remove override");
        MyLog.w("disabled");
        MyLog.d("===============================================================");

        MyLog.setThreadIdVisibility(true);
        MyLog.w("Show thread is");
        MyLog.w("enabled");
        MyLog.d("===============================================================");
    }

    private void setViews() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}

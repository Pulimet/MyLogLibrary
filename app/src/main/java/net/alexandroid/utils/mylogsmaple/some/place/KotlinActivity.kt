package net.alexandroid.utils.mylogsmaple.some.place

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_kotlin.*
import net.alexandroid.utils.mylogkt.*
import net.alexandroid.utils.mylogsmaple.R

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { log() }
    }

    private fun log() {
        justPrintLogs()
        customTag()
        showPackageName()
        hideTime()
        showThreadId()
        printExceptionLog()
    }

    private fun printExceptionLog() {
        logE("Show Exception", t = NullPointerException())
        logD("===============================================================")
    }

    private fun customTag() {
        logD("Custom tag example 1", "CustomTag1")
        logD("Custom tag example 2", "CustomTag2")
    }

    private fun showThreadId() {
        MyLogKt.isThreadIdVisible = true
        logW("Show thread is")
        logW("enabled")
        logD("===============================================================")
    }

    private fun hideTime() {
        MyLogKt.isTimeVisible = false
        logW("Show time disabled")
        logD("Some log for tests")
        logD("===============================================================")
    }

    private fun showPackageName() {
        MyLogKt.isPackageNameVisible = true
        logW("Show package enabled")
        logD("Some log for tests")
        logD("===============================================================")
    }

    private fun justPrintLogs() {
        logD("Empty 1")
        logI("Empty 2")
        logW("Empty 3")
        logE("Empty 4")
        logD("===============================================================")
    }
}

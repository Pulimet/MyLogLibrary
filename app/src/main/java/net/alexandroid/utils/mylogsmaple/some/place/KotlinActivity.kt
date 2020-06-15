package net.alexandroid.utils.mylogsmaple.some.place

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_kotlin.*
import net.alexandroid.utils.mylogkt.*
import net.alexandroid.utils.mylogsmaple.BuildConfig
import net.alexandroid.utils.mylogsmaple.R
import kotlin.concurrent.thread

class KotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin)
        setSupportActionBar(toolbar)

        setParamsFromLocalProperties()

        fab.setOnClickListener { log() }
    }

    private fun setParamsFromLocalProperties() {
        MyLogKt.isThreadNameVisible = BuildConfig.isThreadNameVisible
        MyLogKt.isTimeVisible = BuildConfig.isTimeVisible
        MyLogKt.isPackageNameVisible = BuildConfig.isPackageNameVisible
        MyLogKt.isClassNameVisible = BuildConfig.isClassNameVisible
        MyLogKt.isMethodNameVisible = BuildConfig.isMethodNameVisible
        MyLogKt.isSpacingEnabled = BuildConfig.isSpacingEnabled
        MyLogKt.isLengthShouldWrap = BuildConfig.isLengthShouldWrap
        MyLogKt.classNameLength = BuildConfig.classNameLength
        MyLogKt.methodNameLength = BuildConfig.methodNameLength
    }

    private fun log() {
        justPrintLogs()
        customTag()
        showPackageName()
        hideTime()
        showThreadId()
        printExceptionLog()
        changeTag()
        aFunctionWithVeryLongName()
        space()
    }

    private fun space() {
        logD("Spacing enabled")
        MyLogKt.isSpacingEnabled = false
        logD("Spacing disabled")

        MyLogKt.isSpacingEnabled = true
    }

    private fun aFunctionWithVeryLongName() {
        logD("Wrapping enabled by default")
        MyLogKt.isLengthShouldWrap = false
        logD("Wrapping disabled")

        MyLogKt.isLengthShouldWrap = true
    }

    private fun changeTag() {
        logD("Log with default tag")
        MyLogKt.tag = "CustomDefaultTag"
        logD("Changed default tag")
        MyLogKt.tag = "MyLog"
    }

    private fun printExceptionLog() {
        logE("Show Exception", t = NullPointerException())
        logD("===============================================================")
    }

    private fun customTag() {
        logD("Without custom tag")
        logD("Custom tag example 1", "CustomTag1")
        logD("Custom tag example 2", "CustomTag2")
    }

    private fun showThreadId() {
        logW("Show thread name disabled")
        MyLogKt.isThreadNameVisible = true
        logW("Show thread name enabled")
        thread { logD("Other thread") }
        Thread.sleep(500)
        logD("===============================================================")
        MyLogKt.isThreadNameVisible = false
    }

    private fun hideTime() {
        logD("Default time format")
        MyLogKt.timeFormat = "HH:mm"
        logD("Time format changed")
        MyLogKt.isTimeVisible = false
        logW("Show time disabled")
        logD("===============================================================")
    }

    private fun showPackageName() {
        logW("Show package disabled")
        MyLogKt.packageName = packageName
        MyLogKt.isPackageNameVisible = true
        logW("Show package enabled")
        logD("Some log for tests")
        MyLogKt.isPackageNameVisible = false
        logD("===============================================================")
    }

    private fun justPrintLogs() {
        logD()
        logD("Empty 1")
        logI("Empty 2")
        logW("Empty 3")
        logE("Empty 4")
        logD("===============================================================")
    }
}

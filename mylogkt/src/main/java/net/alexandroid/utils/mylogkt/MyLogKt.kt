package net.alexandroid.utils.mylogkt

import android.content.Context
import android.util.Log
import net.alexandroid.utils.mylogkt.MyLogKt.logIt
import java.io.PrintWriter
import java.io.StringWriter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object MyLogKt {
    private lateinit var packageName: String
    var tag: String = ""
        set(value) {
            field = "$value - "
        }

    var isLogsShown = true
    var isPackageNameVisible = false
    var isThreadIdVisible = false
    var isTimeVisible = true

    @JvmOverloads
    fun init(appContext: Context, tag: String = "MyLog - ", showLogs: Boolean = true) {
        packageName = appContext.packageName
        this.tag = tag
        this.isLogsShown = showLogs
    }

    internal fun logIt(level: Int, msg: String, t: Throwable?, customTag: String?) {
        if (!isLogsShown) return
        if (!::packageName.isInitialized) {
            Log.println(Log.ERROR, tag, "==== MyLog library is not initialized ====")
            return
        }

        val stackTrace = Thread.currentThread().stackTrace
        if (stackTrace.size > 6) {
            val element = stackTrace[6]
            val result = StringBuilder()
            if (isTimeVisible) result.append(getTime()).append(" - ")
            if (isThreadIdVisible) result.append("T:").append(getThreadId()).append(" | ")

            // Class
            val simpleClassName = StringBuilder()
            val fullClassName = element.className
            if (isPackageNameVisible) {
                simpleClassName.append(fullClassName.replace(packageName, ""))
            } else {
                simpleClassName.append(fullClassName.substring(fullClassName.lastIndexOf('.')))
            }

            while (simpleClassName.length < if (isPackageNameVisible) 35 else 15) {
                simpleClassName.append(" ")
            }
            result.append(simpleClassName).append(" # ")

            // Method
            val methodName = java.lang.StringBuilder(element.methodName)
            methodName.append("()")
            while (methodName.length < 25) methodName.append(" ")
            result.append(methodName).append(" => ")

            // Message
            result.append(msg)
            if (t != null) {
                val sw = StringWriter()
                val pw = PrintWriter(sw)
                t.printStackTrace(pw)
                pw.flush()
                result.append("\n Throwable: ")
                result.append(sw.toString())
            }
            Log.println(level, if (customTag == null) tag else "$tag>$customTag", result.toString())
        }
    }

    private fun getThreadId(): StringBuilder? {
        val threadId = StringBuilder(Thread.currentThread().id.toString())
        while (threadId.length < 6) threadId.append(" ")
        return threadId
    }

    private fun getTime(): String? {
        val df: DateFormat = SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault())
        return df.format(Calendar.getInstance().time)
    }
}

@JvmOverloads
fun logV(msg: String, customTag: String? = null, t: Throwable? = null ) {
    logIt(Log.VERBOSE, msg, t, customTag)
}

@JvmOverloads
fun logD(msg: String, customTag: String? = null, t: Throwable? = null ) {
    logIt(Log.DEBUG, msg, t, customTag)
}

@JvmOverloads
fun logI(msg: String, customTag: String? = null, t: Throwable? = null ) {
    logIt(Log.INFO, msg, t, customTag)
}

@JvmOverloads
fun logW(msg: String, customTag: String? = null, t: Throwable? = null ) {
    logIt(Log.WARN, msg, t, customTag)
}

@JvmOverloads
fun logE(msg: String, customTag: String? = null, t: Throwable? = null ) {
    logIt(Log.ERROR, msg, t, customTag)
}

@JvmOverloads
fun logA(msg: String, customTag: String? = null, t: Throwable? = null ) {
    logIt(Log.ASSERT, msg, t, customTag)
}
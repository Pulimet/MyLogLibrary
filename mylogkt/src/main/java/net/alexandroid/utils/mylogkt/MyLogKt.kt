package net.alexandroid.utils.mylogkt

import android.util.Log
import net.alexandroid.utils.mylogkt.MyLogKt.logIt
import java.io.PrintWriter
import java.io.StringWriter
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object MyLogKt {
    var tag: String = "MyLog"

    var packageName = ""
    var isLogsShown = true
    var isThreadNameVisible = false
    var isTimeVisible = true
    var isPackageNameVisible = false
    var isClassNameVisible = true
    var isMethodNameVisible = true

    val classNameLength = 15
    val packageAndClassNameLength = 35
    val methodNameLength = 15
    val threadNameLength = 6
    val timeFormat = "HH:mm:ss.SSS"

    internal fun logIt(level: Int, msg: String, t: Throwable?, customTag: String?) {
        if (!isLogsShown) return

        val stackTrace = Thread.currentThread().stackTrace
        val elementIndex: Int = getElementIndex(stackTrace)
        if (elementIndex == 0) return

        val element = stackTrace[elementIndex]
        val result = StringBuilder()
        if (isTimeVisible) result.append(getTime()).append(" - ")
        if (isThreadNameVisible) result.append("T:").append(getThreadId()).append(" | ")

        if (isClassNameVisible) {
            val tempResultLength = result.length
            val fullClassName = element.className
            if (isPackageNameVisible) {
                result.append(fullClassName.replace(packageName, ""))
            } else {
                result.append(fullClassName.substring(fullClassName.lastIndexOf('.') + 1))
            }

            val classNameFinalLength = result.length - tempResultLength
            val length = if (isPackageNameVisible) packageAndClassNameLength else classNameLength
            addSpaces(result, length - classNameFinalLength)
            result.append(" # ")
        }

        if (isMethodNameVisible) {
            val methodName = element.methodName + "()"
            result.append(methodName)
            addSpaces(result, methodNameLength - methodName.length)
            result.append("=> ")
        }

        // Message
        result.append(msg)

        // Exception
        if (t != null) {
            val sw = StringWriter()
            val pw = PrintWriter(sw)
            t.printStackTrace(pw)
            pw.flush()
            result.append("\n Throwable: ")
            result.append(sw.toString())
        }

        val tag = if (customTag == null) tag else "$tag>$customTag"
        Log.println(level, tag, result.toString())
    }

    private fun addSpaces(result: StringBuilder, spaces: Int) {
        if (spaces > 0) result.append(" ".repeat(spaces))
    }

    private fun getElementIndex(stackTrace: Array<StackTraceElement>?): Int {
        if (stackTrace == null) return 0
        // 0 - .VMStack        # getThreadStackTrace()
        // 1 - .Thread         # getStackTrace()
        // 2 - .MyLogKt        # logIt$mylogkt_debug()
        // 3 - .MyLogKtKt      # logD()
        // 4 - .MyLogKtKt      # logD$default()
        // 5 - .MyLogKtKt      # logD() / .KotlinActivity # justPrintLogs()

        for (i in 2..stackTrace.size) {
            val className = stackTrace[i].className ?: ""
            if (className.contains("MyLogKt")) continue
            return i
        }
        return 0
    }

    private fun getThreadId(): StringBuilder? {
        val threadId = StringBuilder(Thread.currentThread().name)
        while (threadId.length < threadNameLength) threadId.append(" ")
        return threadId
    }

    private fun getTime(): String? {
        val df: DateFormat = SimpleDateFormat(timeFormat, Locale.getDefault())
        return df.format(Calendar.getInstance().time)
    }
}

@JvmOverloads
fun logV(msg: String, customTag: String? = null, t: Throwable? = null) {
    logIt(Log.VERBOSE, msg, t, customTag)
}

@JvmOverloads
fun logD(msg: String, customTag: String? = null, t: Throwable? = null) {
    logIt(Log.DEBUG, msg, t, customTag)
}

@JvmOverloads
fun logI(msg: String, customTag: String? = null, t: Throwable? = null) {
    logIt(Log.INFO, msg, t, customTag)
}

@JvmOverloads
fun logW(msg: String, customTag: String? = null, t: Throwable? = null) {
    logIt(Log.WARN, msg, t, customTag)
}

@JvmOverloads
fun logE(msg: String, customTag: String? = null, t: Throwable? = null) {
    logIt(Log.ERROR, msg, t, customTag)
}

@JvmOverloads
fun logA(msg: String, customTag: String? = null, t: Throwable? = null) {
    logIt(Log.ASSERT, msg, t, customTag)
}
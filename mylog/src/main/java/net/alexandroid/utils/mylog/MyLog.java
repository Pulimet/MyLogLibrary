package net.alexandroid.utils.mylog;

import android.content.Context;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MyLog {
    private static String sTag = "MyLog";
    private static boolean sShowLogs = true;
    private static boolean sIsPackageNameVisible = false;
    private static boolean sIsThreadIdVisible = false;
    private static boolean sIsTimeVisible = true;
    private static boolean sIsRemoveOverride = true;
    private static String sPackageName;

    public static void init(Context appContext) {
        sPackageName = appContext.getPackageName();
    }

    public static void init(Context appContext, String pTag) {
        sPackageName = appContext.getPackageName();
        sTag = pTag;
    }

    public static void init(Context appContext, String pTag, boolean pShowLogs) {
        sPackageName = appContext.getPackageName();
        sTag = pTag;
        sShowLogs = pShowLogs;
    }

    public static void showLogs(boolean pShowLogs) {
        sShowLogs = pShowLogs;
    }

    public static void setTag(String pTag) {
        sTag = pTag + " - ";
    }

    public static void setPackageNameVisibility(boolean newValue) {
        sIsPackageNameVisible = newValue;
    }

    public static void setThreadIdVisibility(boolean newValue) {
        sIsThreadIdVisible = newValue;
    }

    public static void setIsTimeVisible(boolean newValue) {
        sIsTimeVisible = newValue;
    }

    public static void setIsRemoveOverride(boolean newValue) {
        sIsRemoveOverride = newValue;
    }

    public static void v(String msg) {
        if (sShowLogs) logIt(Log.VERBOSE, msg);
    }

    public static void v(String customTag, String msg) {
        if (sShowLogs) logIt(Log.VERBOSE, msg, customTag);
    }

    public static void d(String msg) {
        if (sShowLogs) logIt(Log.DEBUG, msg);
    }

    public static void d(String customTag, String msg) {
        if (sShowLogs) logIt(Log.DEBUG, msg, customTag);
    }

    public static void i(String msg) {
        if (sShowLogs) logIt(Log.INFO, msg);
    }

    public static void i(String customTag, String msg) {
        if (sShowLogs) logIt(Log.INFO, msg, customTag);
    }

    public static void w(String msg) {
        if (sShowLogs) logIt(Log.WARN, msg);
    }

    public static void w(String customTag, String msg) {
        if (sShowLogs) logIt(Log.WARN, msg, customTag);
    }

    public static void e(String msg) {
        if (sShowLogs) logIt(Log.ERROR, msg);
    }

    public static void e(String customTag, String msg) {
        if (sShowLogs) logIt(Log.ERROR, msg, customTag);
    }

    public static void a(String msg) {
        if (sShowLogs) logIt(Log.ASSERT, msg);
    }

    public static void a(String customTag, String msg) {
        if (sShowLogs) logIt(Log.ASSERT, msg, customTag);
    }

    public static void e(String msg, Throwable t) {
        if (sShowLogs) logIt(Log.ERROR, msg, t, null);
    }

    public static void e(String customTag, String msg, Throwable t) {
        if (sShowLogs) logIt(Log.ERROR, msg, t, customTag);
    }

    private static void logIt(int level, String msg) {
        logIt(level, msg, null, null);
    }

    private static void logIt(int level, String msg, String customTag) {
        logIt(level, msg, null, customTag);
    }

    private static void logIt(int level, String msg, Throwable t, String customTag) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace != null && stackTrace.length > 4) {
            StackTraceElement element = stackTrace[4];

            StringBuilder result = new StringBuilder();
            if (sIsTimeVisible) {
                result.append(getTime()).append(" - ");
            }

            if (sIsThreadIdVisible) {
                result.append("T:").append(getThreadId()).append(" | ");
            }

            // Class
            StringBuilder simpleClassName = new StringBuilder();
            String fullClassName = element.getClassName();


            if (sIsPackageNameVisible) {
                simpleClassName.append(fullClassName.replace(sPackageName, ""));
            } else {
                simpleClassName.append(fullClassName.substring(fullClassName.lastIndexOf('.')));
            }

            if (sIsRemoveOverride && simpleClassName.indexOf("$override") != -1) {
                simpleClassName.replace(simpleClassName.indexOf("$override"), simpleClassName.length(), "*");
            }

            while (simpleClassName.length() < (sIsPackageNameVisible ? 35 : 15)) {
                simpleClassName.append(" ");
            }

            result.append(simpleClassName).append(" # ");

            // Method
            StringBuilder methodName = new StringBuilder(element.getMethodName());
            methodName.append("()");
            while (methodName.length() < 25) methodName.append(" ");
            result.append(methodName).append(" => ");

            // Message
            result.append(msg);

            if (t != null) {
                StringWriter sw = new StringWriter();
                PrintWriter pw = new PrintWriter(sw);
                t.printStackTrace(pw);
                pw.flush();
                result.append("\n Throwable: ");
                result.append(sw.toString());
            }

            Log.println(level, customTag == null ? sTag : sTag + ">" + customTag, result.toString());
        }
    }

    private static StringBuilder getThreadId() {
        StringBuilder threadId = new StringBuilder(String.valueOf(Thread.currentThread().getId()));
        while (threadId.length() < 6) threadId.append(" ");
        return threadId;
    }

    private static String getTime() {
        DateFormat df = new SimpleDateFormat("HH:mm:ss.SSS", Locale.getDefault());
        return df.format(Calendar.getInstance().getTime());
    }

}

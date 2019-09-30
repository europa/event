package com.europa.event;

/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


import android.os.SystemClock;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ViewLog {
    public static String TAG = "ViewLog";

    public static boolean DEBUG = Log.isLoggable(TAG, Log.VERBOSE);

    /**
     * {@link Class#getName()} uses reflection and calling it on a potentially hot code path may
     * have some cost. To minimize this cost we fetch class name once here and use it later.
     */
    private static final String CLASS_NAME = ViewLog.class.getName();

    /**
     * Customize the log tag for your application, so that other apps using Volley don't mix their
     * logs with yours. <br>
     * Enable the log property for your tag before starting your app: <br>
     * {@code adb shell setprop log.tag.&lt;tag&gt;}
     */
    public static void setTag(String tag) {
        d("Changing log tag to %s", tag);
        TAG = tag;

        // Reinitialize the DEBUG "constant"
        DEBUG = Log.isLoggable(TAG, Log.VERBOSE);
    }

    public static void v(String format, Object... args) {
        if (DEBUG) {
            Log.v(TAG, buildMessage(format, args));
        }
    }

    public static void d(String format, Object... args) {
        Log.d(TAG, buildMessage(format, args));
    }

    public static void e(String format, Object... args) {
        Log.e(TAG, buildMessage(format, args));
    }

    public static void e(Throwable tr, String format, Object... args) {
        Log.e(TAG, buildMessage(format, args), tr);
    }

    public static void wtf(String format, Object... args) {
        Log.wtf(TAG, buildMessage(format, args));
    }

    public static void wtf(Throwable tr, String format, Object... args) {
        Log.wtf(TAG, buildMessage(format, args), tr);
    }

    /**
     * Formats the caller's provided message and prepends useful info like calling thread ID and
     * method name.
     */
    private static String buildMessage(String format, Object... args) {
        String msg = (args == null) ? format : String.format(Locale.US, format, args);
        StackTraceElement[] trace = new Throwable().fillInStackTrace().getStackTrace();

        String caller = "<unknown>";
        // Walk up the stack looking for the first caller outside of VolleyLog.
        // It will be at least two frames up, so start there.
        for (int i = 2; i < trace.length; i++) {
            String clazz = trace[i].getClassName();
            if (!clazz.equals(ViewLog.CLASS_NAME)) {
                String callingClass = trace[i].getClassName();
                callingClass = callingClass.substring(callingClass.lastIndexOf('.') + 1);
                callingClass = callingClass.substring(callingClass.lastIndexOf('$') + 1);

                caller = callingClass + "." + trace[i].getMethodName();
                break;
            }
        }
        return String.format(Locale.US, "[%d] %s: %s", Thread.currentThread().getId(), caller, msg);
    }

    /**
     * A simple event log with records containing a name, thread ID, and timestamp.
     */
}

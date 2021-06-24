package com.jsb.android_jetpack_practice.utils

import android.util.Log
import com.jsb.android_jetpack_practice.application.JetApplication

object SimpleLog
{
    /**
     * Log Level Error
     */
    fun e(message: String) {
        if (JetApplication.debug) Log.e(Constants.TAG, buildLogMsg(message))
    }

    /**
     * Log Level Warning
     */
    fun w(message: String) {
        if (JetApplication.debug) Log.w(Constants.TAG, buildLogMsg(message))
    }

    /**
     * Log Level Information
     */
    fun i(message: String) {
        if (JetApplication.debug) Log.i(Constants.TAG, buildLogMsg(message))
    }

    /**
     * Log Level Debug
     */
    fun d(message: String) {
        if (JetApplication.debug) Log.d(Constants.TAG, buildLogMsg(message))
    }

    /**
     * Log Level Verbose
     */
    fun v(message: String) {
        if (JetApplication.debug) Log.v(Constants.TAG, buildLogMsg(message))
    }


    fun buildLogMsg(message: String): String {

        val ste = Thread.currentThread().stackTrace[4]

        val sb = StringBuilder()

        sb.append("[")
        sb.append(ste.fileName.replace(".java", ""))
        sb.append("::")
        sb.append(ste.methodName)
        sb.append("]")
        sb.append(message)

        return sb.toString()

    }
}
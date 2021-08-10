package com.jsb.android_jetpack_practice.application

import android.app.Application
import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import com.jsb.android_jetpack_practice.BuildConfig.DEBUG
import com.jsb.android_jetpack_practice.model.database.BeerRepository
import com.jsb.android_jetpack_practice.model.database.BeerRoomDatabase

class JetApplication: Application()
{
    companion object{
        val debug = DEBUG
    }

    override fun onCreate() {
        super.onCreate()
        applicationContext?.let {
            if (DEBUG) {
                isDebuggable(it)
            }
        }
    }

    // For Database
    private val database by lazy { BeerRoomDatabase.getDatabase(this@JetApplication) }
    val repository by lazy { BeerRepository(database.beerDao()) }

    /**
     * @param context
     * @return debuggable
     */
    private fun isDebuggable(context: Context): Boolean {
        var debuggable = false

        try {
            val appInfo: ApplicationInfo = context.packageManager.getApplicationInfo(context.packageName, 0)
            debuggable = (0 != (appInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE))
        } catch (e: PackageManager.NameNotFoundException) {e.stackTrace}

        return debuggable
    }
}
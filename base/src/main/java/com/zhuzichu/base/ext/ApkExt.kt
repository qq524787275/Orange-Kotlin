package com.zhuzichu.base.ext

import android.app.ActivityManager
import android.content.Context
import com.zhuzichu.base.global.AppGlobal

@Suppress("DEPRECATION")
fun getVersionCode(): Int {
    return try {
        AppGlobal.context.packageManager.getPackageInfo(AppGlobal.context.packageName, 0)
            .versionCode
    } catch (e: Exception) {
        0
    }
}

fun getVersionName(): String {
    return try {
        AppGlobal.context.packageManager.getPackageInfo(AppGlobal.context.packageName, 0)
            .versionName
    } catch (e: Exception) {
        ""
    }
}


fun isAppMainProcess(): Boolean {
    val pid = android.os.Process.myPid()
    val processName = getProcessNameByPid(pid)
    if (processName == AppGlobal.context.packageName)
        return true
    return false
}

fun getProcessNameByPid(pid: Int): String {
    val manager = AppGlobal.context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    manager.runningAppProcesses.forEach {
        if (it.pid == pid)
            return it.processName
    }
    return ""
}


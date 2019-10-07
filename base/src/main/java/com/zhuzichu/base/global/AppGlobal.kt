package com.zhuzichu.base.global

import android.app.Application
import android.content.Context

object AppGlobal {
    lateinit var application: Application

    val context: Context by lazy {
        application.applicationContext
    }

    fun init(application: Application) {
        AppGlobal.application = application
        //初始化颜色
    }

}
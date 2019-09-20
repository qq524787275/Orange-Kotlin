package com.zhuzichu.base.global

import android.app.Application

object AppGlobal {
    lateinit var context: Application

    fun init(context: Application) {
        AppGlobal.context = context
        //初始化颜色
    }
}
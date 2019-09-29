package com.zhuzichu.base.ext

import com.zhuzichu.base.global.AppGlobal

fun Int.toStringByResId(): String {
    return AppGlobal.context.getString(this)
}
package com.zhuzichu.base.ext

import android.util.Log

private const val TAG = "zzc"

fun Any.logi(tag: String = TAG) {
    Log.i(tag, this.toString())
}
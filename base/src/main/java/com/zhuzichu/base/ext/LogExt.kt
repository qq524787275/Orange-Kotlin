package com.zhuzichu.base.ext

import timber.log.Timber

private const val TAG = "zzc"

fun Any.logi(tag: String = TAG) {
    Timber.tag(tag).i(this.toString())
}
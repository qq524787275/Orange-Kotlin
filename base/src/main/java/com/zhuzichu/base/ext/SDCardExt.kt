package com.zhuzichu.base.ext

import android.os.Environment

fun isSDCardMounted(): Boolean {
    return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
}

package com.zhuzichu.base.ext

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.NonNull
import com.zhuzichu.base.R
import com.zhuzichu.base.global.AppGlobal

fun toast(message: String, duration: Int = Toast.LENGTH_SHORT) =
    makeText(AppGlobal.context, message, duration).show()

fun Any.toast(duration: Int = Toast.LENGTH_SHORT): Any {
    return makeText(AppGlobal.context, this.toString(), duration).apply {
        show()
    }
}

fun makeText(
    @NonNull context: Context,
    @NonNull text: CharSequence,
    duration: Int
): Toast {
    val result = Toast(context)
    val inflate = LayoutInflater.from(context)
    val tv = inflate.inflate(R.layout.layout_toast, null) as TextView
    tv.text = text
    result.view = tv
    result.setGravity(Gravity.CENTER, 0, 0)
    result.duration = duration
    return result
}
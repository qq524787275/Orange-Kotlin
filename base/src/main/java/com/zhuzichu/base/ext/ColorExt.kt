package com.zhuzichu.base.ext

import android.app.Activity
import androidx.core.content.ContextCompat
import com.google.android.material.R
import com.zhuzichu.base.global.AppGlobal
import android.util.TypedValue
import androidx.annotation.AttrRes


fun Int.toColorByResId(): Int {
    return ContextCompat.getColor(AppGlobal.context, this)
}

@AttrRes
fun Activity.getPrimaryColor(): Int {
    val typedValue = TypedValue()
    this.theme.resolveAttribute(R.attr.colorPrimary, typedValue, true)
    return typedValue.data
}

@AttrRes
fun Activity.getSecondaryColor(): Int {
    val typedValue = TypedValue()
    this.theme.resolveAttribute(R.attr.colorSecondary, typedValue, true)
    return typedValue.data
}
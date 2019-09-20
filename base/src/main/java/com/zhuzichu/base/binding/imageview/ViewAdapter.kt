package com.zhuzichu.base.binding.imageview

import android.widget.ImageView
import androidx.databinding.BindingAdapter


@BindingAdapter("src")
fun bindImageViewSrc(imageView: ImageView, srcId: Int) {
    imageView.setImageResource(srcId)
}
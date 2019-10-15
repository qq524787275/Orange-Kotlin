package com.zhuzichu.base.binding.imageview

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.zhuzichu.base.common.glide.GlideApp

@BindingAdapter(value = ["url", "fadeDuration"], requireAll = false)
fun bindImageViewByUrl(imageView: ImageView, url: Any, fadeDuration: Int = 0) {
    GlideApp.with(imageView)
        .load(url).also {
            if (fadeDuration != 0) {
                it.transition(DrawableTransitionOptions.withCrossFade(fadeDuration))
            }
        }
        .into(imageView)
}
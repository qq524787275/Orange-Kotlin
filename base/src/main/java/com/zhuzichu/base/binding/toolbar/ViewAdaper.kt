package com.zhuzichu.base.binding.toolbar

import android.view.View
import androidx.databinding.BindingAdapter
import com.zhuzichu.base.binding.BindingCommand
import com.zhuzichu.base.widget.NiceToolbar

@BindingAdapter("onClickNavigation")
fun bindToolbar(toolbar: NiceToolbar, clickCommand: BindingCommand<*>?) {
    toolbar.onOnClickNavigationListener = View.OnClickListener {
        clickCommand?.execute()
    }
}
package com.zhuzichu.orange.ui.main.viewmodel

import com.zhuzichu.base.base.BaseViewModel
import com.zhuzichu.base.ext.toast
import javax.inject.Inject


class ViewModelMain @Inject constructor() : BaseViewModel() {
    init {
        "触发了".toast()
    }
}
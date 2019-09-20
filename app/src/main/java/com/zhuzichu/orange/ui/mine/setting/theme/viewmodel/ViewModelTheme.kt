package com.zhuzichu.orange.ui.mine.setting.theme.viewmodel

import android.app.Application
import com.zhuzichu.base.base.BaseViewModel
import com.zhuzichu.base.binding.BindingCommand

class ViewModelTheme(application: Application) : BaseViewModel(application) {
    val onClickNavigation = BindingCommand<Any>({
        back()
    })
}
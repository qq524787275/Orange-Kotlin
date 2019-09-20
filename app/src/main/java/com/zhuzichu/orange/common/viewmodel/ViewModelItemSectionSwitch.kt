package com.zhuzichu.orange.common.viewmodel

import androidx.annotation.StringRes
import com.zhuzichu.base.base.BaseViewModel
import com.zhuzichu.base.base.ItemViewModel
import com.zhuzichu.base.binding.BindingCommand

class ViewModelItemSectionSwitch(
    viewModel: BaseViewModel,
    id: Int,
    @StringRes val textId: Int,
    var isOff: Boolean,
    private var onSwitchEvent: ((parameter: Int) -> Unit)? = null
) : ItemViewModel<BaseViewModel>(viewModel) {

    val onClickItem = BindingCommand<Any>({

    })
}
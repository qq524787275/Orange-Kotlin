package com.zhuzichu.orange.common.viewmodel

import androidx.annotation.StringRes
import com.zhuzichu.base.base.ItemViewModel
import com.zhuzichu.base.binding.BindingCommand

data class ViewModelItemSectionSwitch(
    val id: Int,
    @StringRes val textId: Int,
    var isOff: Boolean,
    private var onSwitchEvent: ((parameter: Int) -> Unit)? = null
) : ItemViewModel() {

    val onClickItem = BindingCommand<Any>({
        onSwitchEvent?.invoke(id)
    })
}
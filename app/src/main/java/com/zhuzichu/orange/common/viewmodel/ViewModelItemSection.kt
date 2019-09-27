package com.zhuzichu.orange.common.viewmodel

import androidx.annotation.StringRes

import com.zhuzichu.base.base.ItemViewModel
import com.zhuzichu.base.binding.BindingCommand

data class ViewModelItemSection(
    val id: Int,
    @StringRes val textId: Int,
    private val onClickEvent: ((parameter: Int) -> Unit)? = null
) : ItemViewModel() {
    val onClickItem = BindingCommand<Int>({
        onClickEvent?.invoke(id)
    })
}
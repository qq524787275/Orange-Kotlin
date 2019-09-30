package com.zhuzichu.orange.common.viewmodel

import com.zhuzichu.base.base.BaseViewModel
import com.zhuzichu.base.base.ItemViewModel
import com.zhuzichu.base.binding.BindingCommand

data class ViewModelItemSectionColor(
    val viewModel: BaseViewModel,
    val textId: Int,
    val color: Int,
    private val onClickEvent: ((parameter: ViewModelItemSectionColor) -> Unit)? = null
) : ItemViewModel() {

    val onClickItem = BindingCommand<Any>({
        onClickEvent?.invoke(this)
    })

}
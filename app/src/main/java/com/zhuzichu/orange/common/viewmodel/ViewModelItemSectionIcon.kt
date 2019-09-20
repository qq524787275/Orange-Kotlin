package com.zhuzichu.orange.common.viewmodel

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.zhuzichu.base.base.BaseViewModel
import com.zhuzichu.base.base.ItemViewModel
import com.zhuzichu.base.binding.BindingCommand

class ViewModelItemSectionIcon(
    viewModel: BaseViewModel,
    id: Int,
    @DrawableRes val iconId: Int,
    @StringRes val textId: Int,
    private var onClickEvent: ((parameter: Int) -> Unit)? = null
) : ItemViewModel<BaseViewModel>(viewModel) {

    val onClickItem = BindingCommand<Int>({
        onClickEvent?.invoke(id)
    })
}
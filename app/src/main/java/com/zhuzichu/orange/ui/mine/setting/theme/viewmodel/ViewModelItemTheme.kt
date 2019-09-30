package com.zhuzichu.orange.ui.mine.setting.theme.viewmodel

import com.zhuzichu.base.base.ItemViewModel
import com.zhuzichu.base.binding.BindingCommand

data class ViewModelItemTheme(
    val viewModel: ViewModelTheme,
    val titleId: Int,
    val mode: Int,
    val isSelected: Boolean
) : ItemViewModel() {

    val onClickItem = BindingCommand<Any>({
        viewModel.items.value = viewModel.items.value?.map {
            if (it is ViewModelItemTheme) {
                it.copy(isSelected = it.titleId == titleId)
            } else {
                it
            }
        }
        viewModel.themeChangeEvent.value =mode
    })
}
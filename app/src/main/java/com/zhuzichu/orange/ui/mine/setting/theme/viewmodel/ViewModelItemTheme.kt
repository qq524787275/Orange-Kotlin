package com.zhuzichu.orange.ui.mine.setting.theme.viewmodel

import com.zhuzichu.base.base.ItemViewModel
import com.zhuzichu.base.binding.BindingCommand
import com.zhuzichu.base.ext.toast

data class ViewModelItemTheme(
    val viewModel: ViewModelTheme,
    val titleId: Int,
    val mode: Int,
    val isSelected: Boolean
) : ItemViewModel() {

    val onClickItem = BindingCommand<Any>({
        viewModel.themeChangeEvent.value = mode
    })
}
package com.zhuzichu.orange.ui.mine.setting.languages.viewmodel

import com.zhuzichu.base.base.ItemViewModel
import com.zhuzichu.base.binding.BindingCommand

data class ViewModelItemLanguage(
    val viewModel: ViewModelLanguages,
    val title: String,
    val locale: String,
    val isSelected: Boolean
) : ItemViewModel() {

    val onClickItem = BindingCommand<Any>({
        viewModel.languagesChangeEvent.value = locale
    })
}
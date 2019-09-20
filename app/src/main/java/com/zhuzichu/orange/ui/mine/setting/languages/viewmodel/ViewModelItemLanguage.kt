package com.zhuzichu.orange.ui.mine.setting.languages.viewmodel

import com.zhuzichu.base.base.ItemViewModel
import com.zhuzichu.base.binding.BindingCommand

class ViewModelItemLanguage(
    viewModel: ViewModelLanguages,
    val title: String,
    val isSelected: Boolean
) : ItemViewModel<ViewModelLanguages>(viewModel) {

    val onClickItem = BindingCommand<Any>({
        viewModel.items.value?.map {

        }
    })
}


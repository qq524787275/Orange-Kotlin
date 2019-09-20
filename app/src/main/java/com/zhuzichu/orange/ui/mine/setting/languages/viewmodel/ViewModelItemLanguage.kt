package com.zhuzichu.orange.ui.mine.setting.languages.viewmodel

import androidx.core.app.ActivityCompat
import com.zhuzichu.base.base.ItemViewModel
import com.zhuzichu.base.binding.BindingCommand
import com.zhuzichu.base.common.preference.UserPreference

data class ViewModelItemLanguage(
    val viewModel: ViewModelLanguages,
    val title: String,
    val local: String,
    val isSelected: Boolean
) : ItemViewModel<ViewModelLanguages>(viewModel) {

   private val userPreference by lazy { UserPreference() }

    val onClickItem = BindingCommand<Any>({
        viewModel.items.value = viewModel.items.value?.map {
            (it as ViewModelItemLanguage).copy(isSelected = it.title == title)
        }
        userPreference.local = local
        ActivityCompat.recreate(viewModel.activityCtx)
    })
}


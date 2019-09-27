package com.zhuzichu.orange.ui.mine.setting.languages.viewmodel

import androidx.core.app.ActivityCompat
import com.zhuzichu.base.base.ItemViewModel
import com.zhuzichu.base.binding.BindingCommand
import com.zhuzichu.base.common.preference.UserPreference
import com.zhuzichu.orange.R

data class ViewModelItemLanguage(
    val viewModel: ViewModelLanguages,
    val title: String,
    val locale: String,
    val isSelected: Boolean
) : ItemViewModel() {

    private val userPreference by lazy { UserPreference() }

    val onClickItem = BindingCommand<Any>({
        viewModel.items.value = viewModel.items.value?.map {
            (it as ViewModelItemLanguage).copy(isSelected = it.title == title)
        }
        userPreference.locale = locale
        viewModel.activityCtx.window.setWindowAnimations(R.style.WindowFadeTheme)
        ActivityCompat.recreate(viewModel.activityCtx)
    })
}
package com.zhuzichu.orange.ui.mine.setting.theme.viewmodel

import androidx.appcompat.app.AppCompatDelegate
import com.zhuzichu.base.base.ItemViewModel
import com.zhuzichu.base.binding.BindingCommand
import com.zhuzichu.base.common.preference.UserPreference
import com.zhuzichu.base.ext.toast
import com.zhuzichu.orange.R

data class ViewModelItemTheme(
    val viewModel: ViewModelTheme,
    val titleId: Int,
    val mode: Int,
    val isSelected: Boolean
) : ItemViewModel() {

    private val userPreference: UserPreference by lazy { UserPreference() }

    val onClickItem = BindingCommand<Any>({
        viewModel.items.value = viewModel.items.value?.map {
            if (it is ViewModelItemTheme) {
                it.copy(isSelected = it.titleId == titleId)
            } else {
                it
            }
        }
        viewModel.activityCtx.window.setWindowAnimations(R.style.WindowFadeTheme)
        userPreference.uiMode = mode
        AppCompatDelegate.setDefaultNightMode(userPreference.uiMode)
    })
}
package com.zhuzichu.orange.common.viewmodel

import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.color.ColorPalette
import com.afollestad.materialdialogs.color.colorChooser
import com.afollestad.materialdialogs.lifecycle.lifecycleOwner
import com.zhuzichu.base.base.BaseViewModel
import com.zhuzichu.base.base.ItemViewModel
import com.zhuzichu.base.binding.BindingCommand
import com.zhuzichu.orange.R

data class ViewModelItemSectionColor(
    val viewModel: BaseViewModel,
    val textId: Int,
    val color: Int
) : ItemViewModel() {

    val onClickItem = BindingCommand<Any>({
        MaterialDialog(viewModel.activityCtx).show {
            cornerRadius(10f)
            title(text = "选择主题颜色")
            colorChooser(
                colors = ColorPalette.Accent,
                subColors = ColorPalette.AccentSub,
                allowCustomArgb = true,
                showAlphaSelector = true
            ) { _, color ->
                when (textId) {
                    R.string.primary_color -> {
                        
                    }
                    R.string.secondary_color -> {

                    }
                    else -> {
                    }
                }
            }
            positiveButton(android.R.string.ok)
            negativeButton(android.R.string.cancel)
            lifecycleOwner(viewModel.fragment)
        }
    })

}
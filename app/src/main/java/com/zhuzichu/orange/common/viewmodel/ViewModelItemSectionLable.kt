package com.zhuzichu.orange.common.viewmodel

import androidx.annotation.StringRes
import com.zhuzichu.base.base.ItemViewModel

data class ViewModelItemSectionLable(
    @StringRes val textId: Int
) : ItemViewModel()
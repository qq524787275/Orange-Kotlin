package com.zhuzichu.orange.ui.mine.setting.theme.viewmodel

import androidx.appcompat.app.AppCompatDelegate.*
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.base.base.BaseViewModel
import com.zhuzichu.base.binding.BindingCommand
import com.zhuzichu.base.ext.getPrimaryColor
import com.zhuzichu.base.ext.getSecondaryColor
import com.zhuzichu.base.ext.map
import com.zhuzichu.orange.BR
import com.zhuzichu.orange.R
import com.zhuzichu.orange.common.viewmodel.ViewModelItemSectionColor
import com.zhuzichu.orange.common.viewmodel.ViewModelItemSectionLable
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject

class ViewModelTheme @Inject constructor() : BaseViewModel() {

    val items = MutableLiveData<List<Any>>()

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ViewModelItemTheme>(BR.item, R.layout.item_theme)
        map<ViewModelItemSectionLable>(BR.item, R.layout.item_section_lable)
        map<ViewModelItemSectionColor>(BR.item, R.layout.item_section_color)
    }

    val onClickNavigation = BindingCommand<Any>({
        back()
    })

    override fun initData() {
        items.value = listOf(
            ViewModelItemSectionLable(R.string.dark_mode),
            ViewModelItemTheme(
                this,
                R.string.settings_theme_light,
                MODE_NIGHT_NO,
                isCurrentModel(MODE_NIGHT_NO)
            ),
            ViewModelItemTheme(
                this,
                R.string.settings_theme_dark,
                MODE_NIGHT_YES,
                isCurrentModel(MODE_NIGHT_YES)
            ),
            ViewModelItemTheme(
                this,
                R.string.settings_theme_system,
                MODE_NIGHT_FOLLOW_SYSTEM,
                isCurrentModel(MODE_NIGHT_FOLLOW_SYSTEM)
            ),
            ViewModelItemTheme(
                this,
                R.string.settings_theme_battery,
                MODE_NIGHT_AUTO_BATTERY,
                isCurrentModel(MODE_NIGHT_AUTO_BATTERY)
            ),
            ViewModelItemSectionLable(R.string.theme_color),
            ViewModelItemSectionColor(
                this,
                R.string.primary_color,
                activityCtx.getPrimaryColor()
            ),
            ViewModelItemSectionColor(
                this,
                R.string.secondary_color,
                activityCtx.getSecondaryColor()
            )
        )
    }

    private fun isCurrentModel(mode: Int): Boolean = mode == getDefaultNightMode()


}
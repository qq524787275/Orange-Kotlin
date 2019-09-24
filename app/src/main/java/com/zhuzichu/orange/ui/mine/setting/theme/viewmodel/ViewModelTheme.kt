package com.zhuzichu.orange.ui.mine.setting.theme.viewmodel

import android.app.Application
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.*
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.base.base.BaseViewModel
import com.zhuzichu.base.binding.BindingCommand
import com.zhuzichu.base.ext.map
import com.zhuzichu.orange.BR
import com.zhuzichu.orange.R
import com.zhuzichu.orange.ui.mine.setting.languages.viewmodel.ViewModelLanguages.Companion.LOCAL_EN
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass

class ViewModelTheme(application: Application) : BaseViewModel(application) {

    val items = MutableLiveData<List<Any>>().also {
        it.value = listOf(
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
            )
        )

    }

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ViewModelItemTheme>(BR.item, R.layout.item_theme)
    }

    val onClickNavigation = BindingCommand<Any>({
        back()
    })

    private fun isCurrentModel(mode: Int): Boolean = mode == getDefaultNightMode()


}
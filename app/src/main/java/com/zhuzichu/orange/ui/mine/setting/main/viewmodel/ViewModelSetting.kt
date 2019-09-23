package com.zhuzichu.orange.ui.mine.setting.main.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.base.base.BaseViewModel
import com.zhuzichu.base.binding.BindingCommand
import com.zhuzichu.base.ext.map
import com.zhuzichu.base.ext.toast
import com.zhuzichu.orange.BR
import com.zhuzichu.orange.R
import com.zhuzichu.orange.common.viewmodel.ViewModelItemSection
import com.zhuzichu.orange.common.viewmodel.ViewModelItemSectionLine
import com.zhuzichu.orange.ui.mine.setting.languages.fragment.FragmentLanguages
import com.zhuzichu.orange.ui.mine.setting.main.fragment.FragmentSetting
import com.zhuzichu.orange.ui.mine.setting.theme.fragment.FragmentTheme
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass

class ViewModelSetting(application: Application) : BaseViewModel(application) {

    companion object {
        const val SETTING_SECTION_LANGUAGES = 0
        const val SETTING_SECTION_THEME=1
    }

    private val onClickSection = { id: Int ->
        when (id) {
            SETTING_SECTION_LANGUAGES -> {
//                startFragment(FragmentLanguages())
            }
            SETTING_SECTION_THEME->{
//                startFragment(FragmentTheme())
            }
            else -> {
            }
        }
    }

    val items = MutableLiveData<List<Any>>().also {
        it.value = listOf(
            ViewModelItemSectionLine(this),
            ViewModelItemSection(
                this,
                SETTING_SECTION_LANGUAGES,
                R.string.languages,
                onClickSection
            ),
            ViewModelItemSection(
                this,
                SETTING_SECTION_THEME,
                R.string.theme,
                onClickSection
            )
        )
    }
    val itemBinding = OnItemBindClass<Any>().apply {
        map<ViewModelItemSection>(BR.item, R.layout.item_section)
        map<ViewModelItemSectionLine>(BR.item, R.layout.item_section_line)
    }

    val onClickNavigation = BindingCommand<Any>({
        back()
    })
}
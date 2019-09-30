package com.zhuzichu.orange.ui.mine.setting.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.base.base.BaseViewModel
import com.zhuzichu.base.binding.BindingCommand
import com.zhuzichu.base.ext.map
import com.zhuzichu.orange.BR
import com.zhuzichu.orange.R
import com.zhuzichu.orange.common.viewmodel.ViewModelItemSection
import com.zhuzichu.orange.common.viewmodel.ViewModelItemSectionLine
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject

class ViewModelSetting @Inject constructor() : BaseViewModel() {

    companion object {
        const val LANGUAGES = 0x00
        const val THEME = 0x01
    }

    private val onClickSection: (Int) -> Unit = {
        when (it) {
            LANGUAGES -> {
                startFragment(R.id.action_fragmentSetting_to_fragmentLanguages)
            }
            THEME -> {
                startFragment(R.id.action_fragmentSetting_to_fragmentTheme)
            }
            else -> {
            }
        }
    }

    val items = MutableLiveData<List<Any>>().also {
        it.value = listOf(
            ViewModelItemSection(
                LANGUAGES,
                R.string.languages,
                onClickSection
            ),
            ViewModelItemSection(
                THEME,
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
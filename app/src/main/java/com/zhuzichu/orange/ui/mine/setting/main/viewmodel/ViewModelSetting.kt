package com.zhuzichu.orange.ui.mine.setting.main.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.request.RequestOptions
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
        const val LANGUAGES = 0x00
        const val THEME = 0x01
    }

    private val onClickSection = { id: Int ->
        when (id) {
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
                this,
                LANGUAGES,
                R.string.languages,
                onClickSection
            ),
            ViewModelItemSection(
                this,
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
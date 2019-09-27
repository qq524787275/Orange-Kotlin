package com.zhuzichu.orange.ui.mine.main.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.base.base.BaseViewModel
import com.zhuzichu.base.ext.map
import com.zhuzichu.orange.BR
import com.zhuzichu.orange.R
import com.zhuzichu.orange.common.viewmodel.ViewModelItemSectionIcon
import com.zhuzichu.orange.common.viewmodel.ViewModelItemSectionLine
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass

class ViewModelMine(application: Application) : BaseViewModel(application) {
    companion object {
        const val MAIN_SECTION_SETTING = 0
    }

    private val onClickSection = { id: Int ->
        when (id) {
            MAIN_SECTION_SETTING -> {
                startFragment(R.id.action_fragmentMain_to_fragmentSetting)
            }
            else -> {
            }
        }
    }

    val items = MutableLiveData<List<Any>>().also {
        it.value = listOf(
            ViewModelItemSectionLine(),
            ViewModelItemSectionIcon(
                MAIN_SECTION_SETTING,
                R.drawable.ic_setting,
                R.string.setting,
                onClickSection
            )
        )
    }

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ViewModelItemSectionIcon>(BR.item, R.layout.item_section_icon)
        map<ViewModelItemSectionLine>(BR.item, R.layout.item_section_line)
    }
}
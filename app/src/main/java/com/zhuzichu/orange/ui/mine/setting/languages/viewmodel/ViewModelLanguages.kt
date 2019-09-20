package com.zhuzichu.orange.ui.mine.setting.languages.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.base.ext.map
import com.zhuzichu.base.base.BaseViewModel
import com.zhuzichu.base.binding.BindingCommand
import com.zhuzichu.orange.BR
import com.zhuzichu.orange.R
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass

class ViewModelLanguages(application: Application) : BaseViewModel(application) {
    val items = MutableLiveData<List<Any>>().also {
        it.value = listOf(
            ViewModelItemLanguage(this, "English", false),
            ViewModelItemLanguage(this, "中文", true),
            ViewModelItemLanguage(this, "عربى", false)
        )
    }

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ViewModelItemLanguage>(BR.item, R.layout.item_language)
    }

    val onClickNavigation = BindingCommand<Any>({
        back()
    })
}
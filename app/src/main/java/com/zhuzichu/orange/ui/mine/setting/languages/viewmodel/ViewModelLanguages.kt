package com.zhuzichu.orange.ui.mine.setting.languages.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.zhuzichu.base.ext.map
import com.zhuzichu.base.base.BaseViewModel
import com.zhuzichu.base.binding.BindingCommand
import com.zhuzichu.base.common.preference.UserPreference
import com.zhuzichu.orange.BR
import com.zhuzichu.orange.R
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass

class ViewModelLanguages(application: Application) : BaseViewModel(application) {

    companion object{
        const val LOCAL_EN="en"
        const val LOCAL_ZH="zh"
        const val LOCAL_AR="ar"
    }

    private val userPreference:UserPreference by lazy { UserPreference() }

    val items = MutableLiveData<List<Any>>().also {
        it.value = listOf(
            ViewModelItemLanguage(this, "English", LOCAL_EN, userPreference.local== LOCAL_EN),
            ViewModelItemLanguage(this, "中文", LOCAL_ZH, userPreference.local== LOCAL_ZH),
            ViewModelItemLanguage(this, "عربى", LOCAL_AR, userPreference.local== LOCAL_AR)
        )
    }

    val itemBinding = OnItemBindClass<Any>().apply {
        map<ViewModelItemLanguage>(BR.item, R.layout.item_language)
    }

    val onClickNavigation = BindingCommand<Any>({
        back()
    })
}
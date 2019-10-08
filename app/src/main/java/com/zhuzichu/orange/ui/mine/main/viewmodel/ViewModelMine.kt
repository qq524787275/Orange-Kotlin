package com.zhuzichu.orange.ui.mine.main.viewmodel

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.base.base.BaseViewModel
import com.zhuzichu.base.binding.BindingCommand
import com.zhuzichu.base.ext.map
import com.zhuzichu.base.ext.toast
import com.zhuzichu.orange.BR
import com.zhuzichu.orange.R
import com.zhuzichu.orange.common.viewmodel.ViewModelItemSectionIcon
import com.zhuzichu.orange.common.viewmodel.ViewModelItemSectionLine
import com.zhuzichu.orange.manager.AccountManager
import com.zhuzichu.orange.ui.account.ActivityAccount
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass
import javax.inject.Inject

class ViewModelMine @Inject constructor(
    private val accountManager: AccountManager
) : BaseViewModel() {
    companion object {
        const val MAIN_SECTION_SETTING = 0
    }

    val userInfo by lazy { accountManager.userInfo }
    val isLogin by lazy { accountManager.isLogin }

    private val onClickSection: (Int) -> Unit = {
        when (it) {
            MAIN_SECTION_SETTING -> {
                startFragment(
                    R.id.action_fragmentMain_to_fragmentSetting
                )
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

    val onClickLogin = BindingCommand<Any>({
        startActivity(ActivityAccount::class.java)
    })

    val onClickEdit = BindingCommand<Any>({
        "去编辑用户信息".toast()
    })

}
package com.zhuzichu.orange.ui.mine.setting.theme.fragment

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import com.zhuzichu.base.base.BaseFragment
import com.zhuzichu.base.base.ParamModelDefault
import com.zhuzichu.base.common.prefs.UserStorage
import com.zhuzichu.base.ext.getPrimaryColor
import com.zhuzichu.base.ext.getSecondaryColor
import com.zhuzichu.orange.BR
import com.zhuzichu.orange.R
import com.zhuzichu.orange.databinding.FragmentThemeBinding
import com.zhuzichu.orange.ui.mine.setting.theme.viewmodel.ViewModelTheme

class FragmentTheme : BaseFragment<ParamModelDefault, FragmentThemeBinding, ViewModelTheme>() {

    private val userStorage: UserStorage by lazy { UserStorage() }

    override fun setLayoutId(): Int = R.layout.fragment_theme

    override fun bindVariableId(): Int = BR.viewModel

    override fun initData() {
        viewModel.loadSectionLable(activityCtx.getPrimaryColor(), activityCtx.getSecondaryColor())
    }

    override fun initVariable() {
        viewModel.themeChangeEvent.observe(this, Observer {
            activityCtx.window.setWindowAnimations(R.style.WindowFadeTheme)
            userStorage.uiMode = it
            AppCompatDelegate.setDefaultNightMode(userStorage.uiMode)
            activityCtx.recreate()
        })
    }
}
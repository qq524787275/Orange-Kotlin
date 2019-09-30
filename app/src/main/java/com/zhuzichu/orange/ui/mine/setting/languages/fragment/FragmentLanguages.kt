package com.zhuzichu.orange.ui.mine.setting.languages.fragment

import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import com.zhuzichu.base.base.BaseFragment
import com.zhuzichu.base.base.DefaultParams
import com.zhuzichu.base.common.prefs.UserStorage
import com.zhuzichu.orange.BR
import com.zhuzichu.orange.R
import com.zhuzichu.orange.databinding.FragmentLanguagesBinding
import com.zhuzichu.orange.ui.mine.setting.languages.viewmodel.ViewModelLanguages

class FragmentLanguages :
    BaseFragment<DefaultParams, FragmentLanguagesBinding, ViewModelLanguages>() {

    private val userStorage by lazy { UserStorage() }

    override fun setLayoutId(): Int = R.layout.fragment_languages

    override fun bindVariableId(): Int = BR.viewModel

    override fun initVariable() {
        viewModel.languagesChangeEvent.observe(this, Observer {
            userStorage.locale = it
            activityCtx.window.setWindowAnimations(R.style.WindowFadeTheme)
            ActivityCompat.recreate(activityCtx)
        })
    }
}
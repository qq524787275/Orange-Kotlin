package com.zhuzichu.orange.ui.mine.setting.theme.fragment

import com.zhuzichu.base.base.BaseFragment
import com.zhuzichu.base.base.DefaultParams
import com.zhuzichu.orange.BR
import com.zhuzichu.orange.R
import com.zhuzichu.orange.databinding.FragmentThemeBinding
import com.zhuzichu.orange.ui.mine.setting.theme.viewmodel.ViewModelTheme

class FragmentTheme: BaseFragment<DefaultParams, FragmentThemeBinding, ViewModelTheme>() {
    override fun setLayoutId(): Int=R.layout.fragment_theme

    override fun bindVariableId(): Int =BR.viewModel
}
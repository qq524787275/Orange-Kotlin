package com.zhuzichu.orange.ui.mine.setting.languages.fragment

import com.zhuzichu.base.base.BaseFragment
import com.zhuzichu.base.base.DefaultParams
import com.zhuzichu.orange.BR
import com.zhuzichu.orange.R
import com.zhuzichu.orange.databinding.FragmentLanguagesBinding
import com.zhuzichu.orange.ui.mine.setting.languages.viewmodel.ViewModelLanguages

class FragmentLanguages :
    BaseFragment<DefaultParams, FragmentLanguagesBinding, ViewModelLanguages>() {
    override fun setLayoutId(): Int = R.layout.fragment_languages

    override fun bindVariableId(): Int = BR.viewModel
}
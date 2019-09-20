package com.zhuzichu.orange.ui.home.fragment

import com.zhuzichu.base.base.BaseFragment
import com.zhuzichu.base.base.DefaultParams
import com.zhuzichu.orange.BR
import com.zhuzichu.orange.R
import com.zhuzichu.orange.databinding.FragmentHomeBinding
import com.zhuzichu.orange.ui.home.viewmodel.ViewModelHome

class FragmentHome : BaseFragment<DefaultParams, FragmentHomeBinding, ViewModelHome>() {
    override fun setLayoutId(): Int= R.layout.fragment_home

    override fun bindVariableId(): Int = BR.viewModel
}
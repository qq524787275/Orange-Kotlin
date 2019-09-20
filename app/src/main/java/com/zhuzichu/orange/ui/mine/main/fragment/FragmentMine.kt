package com.zhuzichu.orange.ui.mine.main.fragment

import com.zhuzichu.base.base.BaseFragment
import com.zhuzichu.base.base.DefaultParams
import com.zhuzichu.orange.BR
import com.zhuzichu.orange.R
import com.zhuzichu.orange.databinding.FragmentMineBinding
import com.zhuzichu.orange.ui.mine.main.viewmodel.ViewModelMine

class FragmentMine : BaseFragment<DefaultParams, FragmentMineBinding, ViewModelMine>() {
    override fun setLayoutId(): Int = R.layout.fragment_mine

    override fun bindVariableId(): Int = BR.viewModel
}
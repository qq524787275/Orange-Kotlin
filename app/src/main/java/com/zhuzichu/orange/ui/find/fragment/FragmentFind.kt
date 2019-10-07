package com.zhuzichu.orange.ui.find.fragment

import com.zhuzichu.base.base.BaseFragment
import com.zhuzichu.base.base.ParamModelDefault
import com.zhuzichu.orange.BR
import com.zhuzichu.orange.R
import com.zhuzichu.orange.databinding.FragmentFindBinding
import com.zhuzichu.orange.ui.find.viewmodel.ViewModelFind


class FragmentFind : BaseFragment<ParamModelDefault, FragmentFindBinding, ViewModelFind>() {

    override fun setLayoutId(): Int= R.layout.fragment_find

    override fun bindVariableId(): Int = BR.viewModel

}
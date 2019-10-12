package com.zhuzichu.orange.ui.account.register.fragment

import com.zhuzichu.base.base.BaseFragment
import com.zhuzichu.base.base.ParamModelDefault
import com.zhuzichu.orange.BR
import com.zhuzichu.orange.R
import com.zhuzichu.orange.databinding.FragmentRegisterBinding
import com.zhuzichu.orange.ui.account.register.viewmodel.ViewModelRegister

class FragmentRegister :
    BaseFragment<ParamModelDefault, FragmentRegisterBinding, ViewModelRegister>() {

    override fun setLayoutId(): Int = R.layout.fragment_register

    override fun bindVariableId(): Int = BR.viewModel
}
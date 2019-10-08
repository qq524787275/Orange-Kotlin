package com.zhuzichu.orange.ui.account.login.fragment

import com.zhuzichu.base.base.BaseFragment
import com.zhuzichu.base.base.ParamModelDefault
import com.zhuzichu.orange.BR
import com.zhuzichu.orange.R
import com.zhuzichu.orange.databinding.FragmentLoginBinding
import com.zhuzichu.orange.ui.account.login.viewmodel.ViewModelLogin

class FragmentLogin : BaseFragment<ParamModelDefault, FragmentLoginBinding, ViewModelLogin>() {

    override fun setLayoutId(): Int = R.layout.fragment_login

    override fun bindVariableId(): Int = BR.viewModel

}
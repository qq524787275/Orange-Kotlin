package com.zhuzichu.orange.ui.mine.setting.main.fragment

import com.zhuzichu.base.base.BaseFragment
import com.zhuzichu.base.base.DefaultParams
import com.zhuzichu.base.ext.toast
import com.zhuzichu.orange.BR
import com.zhuzichu.orange.R
import com.zhuzichu.orange.databinding.FragmentSettingBinding
import com.zhuzichu.orange.ui.mine.setting.main.viewmodel.ViewModelSetting

class FragmentSetting : BaseFragment<DefaultParams, FragmentSettingBinding, ViewModelSetting>() {

    override fun setLayoutId(): Int = R.layout.fragment_setting

    override fun bindVariableId(): Int = BR.viewModel



}
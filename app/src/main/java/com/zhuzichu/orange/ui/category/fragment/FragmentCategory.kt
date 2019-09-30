package com.zhuzichu.orange.ui.category.fragment

import com.zhuzichu.base.base.BaseFragment
import com.zhuzichu.base.base.DefaultParams
import com.zhuzichu.orange.BR
import com.zhuzichu.orange.R
import com.zhuzichu.orange.databinding.FragmentCategoryBinding
import com.zhuzichu.orange.ui.category.viewmodel.ViewModelCategory

class FragmentCategory : BaseFragment<DefaultParams, FragmentCategoryBinding, ViewModelCategory>() {

    override fun setLayoutId(): Int = R.layout.fragment_category

    override fun bindVariableId(): Int = BR.viewModel

}
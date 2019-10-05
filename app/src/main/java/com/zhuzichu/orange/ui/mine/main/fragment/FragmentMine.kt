package com.zhuzichu.orange.ui.mine.main.fragment

import com.zhuzichu.base.base.BaseFragment
import com.zhuzichu.base.base.DefaultParams
import com.zhuzichu.orange.BR
import com.zhuzichu.orange.R
import com.zhuzichu.orange.common.viewmodel.ViewModelItemSectionIcon
import com.zhuzichu.orange.common.viewmodel.ViewModelItemSectionLine
import com.zhuzichu.orange.databinding.FragmentMineBinding
import com.zhuzichu.orange.ui.mine.main.viewmodel.ViewModelMine
import com.zhuzichu.orange.ui.mine.main.viewmodel.ViewModelMine.Companion.MAIN_SECTION_SETTING

class FragmentMine : BaseFragment<DefaultParams, FragmentMineBinding, ViewModelMine>() {

    override fun setLayoutId(): Int = R.layout.fragment_mine

    override fun bindVariableId(): Int = BR.viewModel

    override fun initData() {
        viewModel.items.value = listOf(
            ViewModelItemSectionLine(),
            ViewModelItemSectionIcon(
                MAIN_SECTION_SETTING,
                R.drawable.ic_setting,
                R.string.setting,
                viewModel.onClickSection
            )
        )
    }

}
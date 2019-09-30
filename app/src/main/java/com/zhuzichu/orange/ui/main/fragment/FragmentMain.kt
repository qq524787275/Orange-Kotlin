package com.zhuzichu.orange.ui.main.fragment

import androidx.fragment.app.Fragment
import com.zhuzichu.base.base.BaseFragment
import com.zhuzichu.base.base.DefaultFragmentPagerAdapter
import com.zhuzichu.base.base.DefaultParams
import com.zhuzichu.base.ext.setupWithViewPager
import com.zhuzichu.orange.BR
import com.zhuzichu.orange.R
import com.zhuzichu.orange.databinding.FragmentMainBinding
import com.zhuzichu.orange.ui.category.fragment.FragmentCategory
import com.zhuzichu.orange.ui.find.fragment.FragmentFind
import com.zhuzichu.orange.ui.home.fragment.FragmentHome
import com.zhuzichu.orange.ui.main.viewmodel.ViewModelMain
import com.zhuzichu.orange.ui.mine.main.fragment.FragmentMine
import kotlinx.android.synthetic.main.fragment_main.*

class FragmentMain : BaseFragment<DefaultParams, FragmentMainBinding, ViewModelMain>() {

    private val fragments = listOf<Fragment>(
        FragmentHome(),
        FragmentCategory(),
        FragmentFind(),
        FragmentMine()
    )

    override fun setLayoutId(): Int = R.layout.fragment_main

    override fun bindVariableId(): Int = BR.viewModel

    override fun initView() {
        content.offscreenPageLimit = fragments.size
        content.adapter = DefaultFragmentPagerAdapter(
            childFragmentManager, fragments, listOf(
                R.string.home, R.string.category, R.string.find, R.string.mine
            )
        )
        bottom.setupWithViewPager(content)
    }
}
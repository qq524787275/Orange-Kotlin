package com.zhuzichu.orange.ui.main.fragment

import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.zhuzichu.base.base.BaseFragment
import com.zhuzichu.base.base.DefaultFragmentPagerAdapter
import com.zhuzichu.base.base.ParamModelDefault
import com.zhuzichu.base.ext.makeText
import com.zhuzichu.base.ext.setupWithViewPager
import com.zhuzichu.base.ext.toStringByResId
import com.zhuzichu.base.ext.toast
import com.zhuzichu.orange.BR
import com.zhuzichu.orange.R
import com.zhuzichu.orange.databinding.FragmentMainBinding
import com.zhuzichu.orange.ui.category.fragment.FragmentCategory
import com.zhuzichu.orange.ui.find.fragment.FragmentFind
import com.zhuzichu.orange.ui.home.fragment.FragmentHome
import com.zhuzichu.orange.ui.main.viewmodel.ViewModelMain
import com.zhuzichu.orange.ui.mine.main.fragment.FragmentMine
import kotlinx.android.synthetic.main.fragment_main.*
import kotlin.system.exitProcess

class FragmentMain : BaseFragment<ParamModelDefault, FragmentMainBinding, ViewModelMain>() {

    private val waitTime = 2000L
    private var touchTime: Long = 0

    override fun setLayoutId(): Int = R.layout.fragment_main

    override fun bindVariableId(): Int = BR.viewModel

    override fun initView() {

        val fragments = listOf<Fragment>(
            FragmentHome(),
            FragmentCategory(),
            FragmentFind(),
            FragmentMine()
        )

        val titles = listOf(
            R.string.home,
            R.string.category,
            R.string.find,
            R.string.mine
        )

        content.offscreenPageLimit = fragments.size
        content.adapter = DefaultFragmentPagerAdapter(childFragmentManager, fragments, titles)
        bottom.setupWithViewPager(content)
        initBackListener()
    }

    private fun initBackListener() {
        requireActivity().onBackPressedDispatcher.addCallback(this) {
            if (System.currentTimeMillis() - touchTime < waitTime) {
                requireActivity().finish()
                Thread {
                    Thread.sleep(300)
                    exitProcess(0)
                }.start()
            } else {
                touchTime = System.currentTimeMillis()
                R.string.press_again_exit.toast(context = requireContext())
            }
        }
    }
}
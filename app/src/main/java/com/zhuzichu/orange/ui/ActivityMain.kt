package com.zhuzichu.orange.ui

import com.zhuzichu.base.base.BaseActivity
import com.zhuzichu.base.widget.FadeAnimator
import com.zhuzichu.orange.ui.main.fragment.FragmentMain
import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator
import me.yokeyword.fragmentation.anim.FragmentAnimator

class ActivityMain : BaseActivity() {

    override fun setRootFragment(): ISupportFragment = FragmentMain()

    override fun onCreateFragmentAnimator(): FragmentAnimator {
        return DefaultHorizontalAnimator()
    }
}
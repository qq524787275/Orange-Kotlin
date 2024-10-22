package com.zhuzichu.base.base

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.zhuzichu.base.ext.toStringByResId

class DefaultFragmentPagerAdapter(
    fm: FragmentManager,
    private val list: List<Fragment>,
    private val titles: List<Int>? = null
) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getItem(position: Int): Fragment = list[position]

    override fun getCount(): Int = list.size

    override fun getPageTitle(position: Int): CharSequence? {
        return if (titles == null)
            super.getPageTitle(position)
        else
            titles[position].toStringByResId()
    }

}
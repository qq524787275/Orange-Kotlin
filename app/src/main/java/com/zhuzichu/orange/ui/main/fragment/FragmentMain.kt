package com.zhuzichu.orange.ui.main.fragment

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.zhuzichu.base.base.BaseFragment
import com.zhuzichu.base.base.DefaultParams
import com.zhuzichu.base.ext.setupWithNavController
import com.zhuzichu.base.ext.toast
import com.zhuzichu.orange.BR
import com.zhuzichu.orange.R
import com.zhuzichu.orange.databinding.FragmentMainBinding
import com.zhuzichu.orange.ui.main.viewmodel.ViewModelMain
import kotlinx.android.synthetic.main.fragment_main.*

class FragmentMain : BaseFragment<DefaultParams, FragmentMainBinding, ViewModelMain>() {

    private var currentNavController: LiveData<NavController>? = null

    private val navGraphIds = listOf(
        R.navigation.navigation_home,
        R.navigation.navigation_category,
        R.navigation.navigation_find,
        R.navigation.navigation_mine
    )

    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        setupBottomNavigationBar()
    }

    override fun setLayoutId(): Int = R.layout.fragment_main

    override fun bindVariableId(): Int = BR.viewModel

    override fun initView() {
        setupBottomNavigationBar()
    }

    private fun setupBottomNavigationBar() {
        val controller = bottom.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = childFragmentManager,
            containerId = R.id.content,
            intent = activityCtx.intent
        )
        currentNavController = controller
    }

}
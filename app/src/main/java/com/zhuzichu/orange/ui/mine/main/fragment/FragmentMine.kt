package com.zhuzichu.orange.ui.mine.main.fragment

import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import com.zhuzichu.base.base.BaseFragment
import com.zhuzichu.base.base.DefaultParams
import com.zhuzichu.base.ext.toast
import com.zhuzichu.orange.BR
import com.zhuzichu.orange.R
import com.zhuzichu.orange.databinding.FragmentMineBinding
import com.zhuzichu.orange.ui.mine.main.viewmodel.ViewModelMine

class FragmentMine : BaseFragment<DefaultParams, FragmentMineBinding, ViewModelMine>() {
    override fun setLayoutId(): Int = R.layout.fragment_mine

    override fun bindVariableId(): Int = BR.viewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
package com.zhuzichu.orange.ui.home.fragment

import androidx.navigation.NavDeepLinkBuilder
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.autoDispose
import com.zhuzichu.base.base.BaseFragment
import com.zhuzichu.base.base.ParamModelDefault
import com.zhuzichu.orange.BR
import com.zhuzichu.orange.databinding.FragmentHomeBinding
import com.zhuzichu.orange.ui.home.viewmodel.ViewModelHome
import kotlinx.android.synthetic.main.fragment_home.*
import com.zhuzichu.base.ext.toast
import com.zhuzichu.notify.NotifyManager
import com.zhuzichu.orange.R
import javax.inject.Inject


class FragmentHome : BaseFragment<ParamModelDefault, FragmentHomeBinding, ViewModelHome>() {

    @Inject
    lateinit var notifyManager: NotifyManager

    override fun setLayoutId(): Int = R.layout.fragment_home

    override fun bindVariableId(): Int = BR.viewModel

    override fun initView() {
        super.initView()
        title.clicks().autoDispose(title).subscribe({
            "出发了".toast()
            createNotification()
        }, {
            it.printStackTrace()
        })
    }

    private fun createNotification() {
        val pendingIntent = NavDeepLinkBuilder(requireActivity())
            .setGraph(R.navigation.navigation_main)
            .setDestination(R.id.fragmentLanguages)
            .createPendingIntent()
        notifyManager.getCreator()
            .meta {
                clickIntent = pendingIntent
            }
            .content {
                title = "有一条新消息"
                text = "Deeplink跳转到切换语言界面"
            }
            .show(1)
    }

}
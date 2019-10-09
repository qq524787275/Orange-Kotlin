package com.zhuzichu.orange.ui.home.fragment

import android.app.NotificationManager
import androidx.navigation.NavDeepLinkBuilder
import com.jakewharton.rxbinding3.view.clicks
import com.uber.autodispose.android.autoDispose
import com.zhuzichu.base.base.BaseFragment
import com.zhuzichu.base.base.ParamModelDefault
import com.zhuzichu.orange.BR
import com.zhuzichu.orange.databinding.FragmentHomeBinding
import com.zhuzichu.orange.ui.home.viewmodel.ViewModelHome
import kotlinx.android.synthetic.main.fragment_home.*
import android.content.Context
import com.zhuzichu.base.ext.toast
import com.zhuzichu.base.notify.Notify
import com.zhuzichu.orange.R


class FragmentHome : BaseFragment<ParamModelDefault, FragmentHomeBinding, ViewModelHome>() {

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

        val contentIntent = Notify.with(requireContext())
            .content {
                title = "哈哈哈哈"
            }.asBuilder().setContentIntent(pendingIntent)

        val notificationManager =
            requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager.notify(1, contentIntent.build())
    }

}
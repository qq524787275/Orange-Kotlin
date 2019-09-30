package com.zhuzichu.base.base

import android.os.Bundle

interface IBaseFragment {

    fun initViewObservable() {}

    fun initVariable() {}

    fun initView() {}

    fun initData() {}

    fun onSupportVisible() {}

    fun onLazyInitView(saveInstanceState: Bundle?) {}

    fun onSupportInvisible() {}

    fun isSupportVisible(): Boolean

}
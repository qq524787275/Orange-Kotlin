package com.zhuzichu.base.base

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.zhuzichu.base.event.SingleLiveEvent

open class BaseViewModel(application: Application) : AndroidViewModel(application), IBaseViewModel {

    lateinit var fragment: Fragment
    lateinit var activityCtx: Activity
    lateinit var lifecycleProvider: AndroidLifecycleScopeProvider

    val uc by lazy { UIChangeLiveData() }

    fun injectFragment(fragment: Fragment) {
        this.fragment = fragment
    }

    fun injectActivity(activity: Activity) {
        this.activityCtx = activity
    }

    fun injectLifecycleProvider(lifecycleProvider: AndroidLifecycleScopeProvider) {
        this.lifecycleProvider = lifecycleProvider
    }

    fun startActivity(
        clz: Class<*>,
        params: BaseParams = DefaultParams(),
        isPop: Boolean = false,
        options: Bundle = bundleOf(),
        requestCode: Int = 0
    ) {
        val map = HashMap<String, Any>()
        map[Const.CLASS] = clz
        map[Const.PARAMS] = params
        map[Const.POP] = isPop
        map[Const.OPTIONS] = options
        map[Const.REQUEST_CODE] = requestCode
        uc.startActivityEvent.postValue(map)
    }

    fun startFragment(
        actionId: Int,
        params: BaseParams = DefaultParams()
    ) {
        val map = HashMap<String, Any>()
        map[Const.PARAMS] = params
        map[Const.ACTION_ID] = actionId
        uc.startFragmentEvent.postValue(map)
    }


    fun back() {
        uc.onBackPressedEvent.call()
    }



    inner class UIChangeLiveData {
        val startActivityEvent: SingleLiveEvent<Map<String, Any>> = SingleLiveEvent()
        val startFragmentEvent: SingleLiveEvent<Map<String, Any>> = SingleLiveEvent()
        val onBackPressedEvent: SingleLiveEvent<Any> = SingleLiveEvent()
    }

}
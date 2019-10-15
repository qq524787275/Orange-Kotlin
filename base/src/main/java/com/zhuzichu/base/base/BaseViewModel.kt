package com.zhuzichu.base.base

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.core.os.bundleOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.zhuzichu.base.event.SingleLiveEvent
import com.zhuzichu.base.http.exception.ResponseThrowable

open class BaseViewModel : ViewModel(), IBaseViewModel {

    val uc by lazy { UIChangeLiveData() }
    lateinit var lifecycleOwner: LifecycleOwner

    fun startActivity(
        clz: Class<*>,
        paramModel: BaseParamModel = ParamModelDefault(),
        isPop: Boolean = false,
        options: Bundle = bundleOf(),
        requestCode: Int = 0
    ) {
        val playload = Payload.Activity(clz)
        playload.paramModel = paramModel
        playload.isPop = isPop
        playload.options = options
        playload.requestCode = requestCode
        uc.startActivityEvent.value = playload
    }

    fun startFragment(actionId: Int, paramModel: BaseParamModel = ParamModelDefault()) {
        val playload = Payload.Fragment(actionId)
        playload.paramModel = paramModel
        uc.startFragmentEvent.value = playload
    }

    fun back() {
        uc.onBackPressedEvent.call()
    }

    fun showLoading() {
        uc.showLoadingEvent.call()
    }

    fun hideLoading() {
        uc.hideLoadingEvent.call()
    }

    fun handleThrowable(throwable: Throwable) {
        when (throwable) {
            is ResponseThrowable -> toast(throwable.msg)
        }
        throwable.printStackTrace()
    }

    fun injectLifecycleOwner(viewLifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = viewLifecycleOwner
    }

    fun toast(text: String?) {
        uc.toastStringEvent.value = text
    }

    fun toast(@StringRes id: Int) {
        uc.toastStringResEvent.value = id
    }

    inner class UIChangeLiveData {
        internal val startActivityEvent: SingleLiveEvent<Payload.Activity> = SingleLiveEvent()
        internal val startFragmentEvent: SingleLiveEvent<Payload.Fragment> = SingleLiveEvent()
        internal val onBackPressedEvent: SingleLiveEvent<Any> = SingleLiveEvent()
        internal val showLoadingEvent: SingleLiveEvent<Any> = SingleLiveEvent()
        internal val hideLoadingEvent: SingleLiveEvent<Any> = SingleLiveEvent()
        internal val toastStringEvent: SingleLiveEvent<String?> = SingleLiveEvent()
        internal val toastStringResEvent: SingleLiveEvent<Int> = SingleLiveEvent()
    }

}
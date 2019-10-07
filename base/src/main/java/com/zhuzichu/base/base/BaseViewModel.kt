package com.zhuzichu.base.base

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModel
import com.zhuzichu.base.event.SingleLiveEvent

open class BaseViewModel : ViewModel(), IBaseViewModel {

    val uc by lazy { UIChangeLiveData() }

    fun startActivity(
        clz: Class<*>,
        paramModel: BaseParamModel = ParamModelDefault(),
        isPop: Boolean = false,
        options: Bundle = bundleOf(),
        requestCode: Int = 0
    ) {
        val map = HashMap<String, Any>()
        map[Const.CLASS] = clz
        map[Const.PARAMS] = paramModel
        map[Const.POP] = isPop
        map[Const.OPTIONS] = options
        map[Const.REQUEST_CODE] = requestCode
        uc.startActivityEvent.postValue(map)
    }

    fun startFragment(
        actionId: Int,
        paramModel: BaseParamModel = ParamModelDefault()
    ) {
        val map = HashMap<String, Any>()
        map[Const.PARAMS] = paramModel
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
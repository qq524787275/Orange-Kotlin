package com.zhuzichu.base.base

import android.os.Bundle
import androidx.core.os.bundleOf

internal sealed class Payload {

    data class Activity(
        var clz: Class<*>,
        var paramModel: BaseParamModel = ParamModelDefault(),
        var isPop: Boolean = false,
        var options: Bundle = bundleOf(),
        var requestCode: Int = 0
    )

    data class Fragment(
        var actionId: Int,
        var paramModel: BaseParamModel = ParamModelDefault()
    )

}
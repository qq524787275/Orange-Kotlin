package com.zhuzichu.base.entity

data class HeaderParams(
    var platform: String? = null,
    var device: String? = null,
    var versionCode: Int? = null,
    var versionName: String? = null
)
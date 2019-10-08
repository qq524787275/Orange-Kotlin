package com.zhuzichu.orange.entity

data class BeanUserInfo(
    var avatarUrl: String = "",
    var id: Int = 0,
    var loginLastDevice: String = "",
    var loginLastIp: String = "",
    var loginLastPlatform: String = "",
    var loginLastTime: Long = 0,
    var loginLastVersionCode: Int = 0,
    var loginLastVersionName: String = "",
    var nickname: String = "",
    var phone: String = "",
    var registTime: Long = 0,
    var sex: Int = 0,
    var username: String = "",
    var email: String = "",
    var location: String = "",
    var summary: String = ""
)
package com.zhuzichu.orange.ui.account.login.entity

data class BeanToken(
    var token: String = "",
    var userInfo: BeanUserInfo = BeanUserInfo()
)
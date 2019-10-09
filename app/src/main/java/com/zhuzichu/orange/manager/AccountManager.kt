package com.zhuzichu.orange.manager

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.orange.ui.account.login.entity.BeanUserInfo

class AccountManager {
    val userInfo = MutableLiveData<BeanUserInfo>()
    val isLogin = MutableLiveData<Boolean>()
}
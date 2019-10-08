package com.zhuzichu.orange.manager

import androidx.lifecycle.MutableLiveData
import com.zhuzichu.orange.entity.BeanUserInfo

class AccountManager {
    val userInfo = MutableLiveData<BeanUserInfo>()
    val isLogin = MutableLiveData<Boolean>()
}
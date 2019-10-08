package com.zhuzichu.orange.repository

import com.zhuzichu.base.entity.BaseRes
import com.zhuzichu.orange.entity.BeanToken
import io.reactivex.Flowable

class NetRepositoryImpl : NetRepository, IService {
    override fun login(username: String, password: String): Flowable<BaseRes<BeanToken>> {
        return getAppService().login(username, password)
    }
}
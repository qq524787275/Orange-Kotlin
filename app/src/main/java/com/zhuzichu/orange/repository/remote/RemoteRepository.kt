package com.zhuzichu.orange.repository.remote

import com.zhuzichu.base.entity.BaseRes
import com.zhuzichu.orange.repository.AppService
import com.zhuzichu.orange.ui.account.login.entity.BeanToken
import io.reactivex.Flowable
import retrofit2.Retrofit


interface RemoteRepository {
    fun login(
        username: String,
        password: String
    ): Flowable<BaseRes<BeanToken>>
}

class RemoteRepositoryImpl(
    private val appRetrofit: Retrofit
) : RemoteRepository {

    private val appService by lazy { appRetrofit.create(AppService::class.java) }

    override fun login(username: String, password: String): Flowable<BaseRes<BeanToken>> {
        return appService.login(username, password)
    }
}
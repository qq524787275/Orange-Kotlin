package com.zhuzichu.orange.ui.account.login.domain

import com.zhuzichu.base.entity.BaseRes
import com.zhuzichu.base.ext.*
import com.zhuzichu.orange.repository.remote.RemoteRepository
import com.zhuzichu.orange.ui.account.login.entity.BeanToken
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseLogin @Inject constructor(
    private val remoteRepository: RemoteRepository
) {

    fun login(username: String, password: String): Flowable<BaseRes<BeanToken>> {
        return remoteRepository.login(username, password.md5())
            .bindToSchedulers()
            .bindToException()
    }
}
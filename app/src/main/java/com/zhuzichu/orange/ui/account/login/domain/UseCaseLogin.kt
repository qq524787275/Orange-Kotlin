package com.zhuzichu.orange.ui.account.login.domain

import com.zhuzichu.base.domain.UseCase
import com.zhuzichu.base.entity.BaseRes
import com.zhuzichu.base.ext.*
import com.zhuzichu.orange.repository.remote.RemoteRepository
import com.zhuzichu.orange.ui.account.login.entity.BeanToken
import io.reactivex.Flowable
import javax.inject.Inject

class UseCaseLogin @Inject constructor(
    private val remoteRepository: RemoteRepository
) : UseCase<ParameterLogin, Flowable<BaseRes<BeanToken>>>() {

    override fun execute(parameters: ParameterLogin): Flowable<BaseRes<BeanToken>> {
        return remoteRepository.login(parameters.username, parameters.password.md5())
            .bindToSchedulers()
            .bindToException()
    }
}

data class ParameterLogin(
    val username: String,
    val password: String
)
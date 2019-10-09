package com.zhuzichu.orange.ui.account.login.usecase

import com.zhuzichu.base.entity.BaseRes
import com.zhuzichu.orange.repository.remote.RemoteRepository
import com.zhuzichu.orange.ui.account.login.entity.BeanToken
import com.zhuzichu.orange.usecase.UseCase
import io.reactivex.Flowable
import javax.inject.Inject

open class UseCaseLogin @Inject constructor(
    private val repository: RemoteRepository
) : UseCase<ParameterLogin, Flowable<BaseRes<BeanToken>>>() {

    override fun execute(parameters: ParameterLogin): Flowable<BaseRes<BeanToken>> =
        repository.login(username = parameters.username, password = parameters.password)

}

data class ParameterLogin(
    val username: String,
    val password: String
)
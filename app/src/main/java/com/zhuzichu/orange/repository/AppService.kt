package com.zhuzichu.orange.repository

import com.zhuzichu.base.entity.BaseRes
import com.zhuzichu.orange.entity.BeanToken
import io.reactivex.Flowable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * desc:  <br/>
 * time: 2019/10/8 15:51 <br/>
 * author: Coffee <br/>
 * since V 1.2 <br/>
 */
interface AppService {
    @FormUrlEncoded
    @POST("api/user/login")
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Flowable<BaseRes<BeanToken>>
}
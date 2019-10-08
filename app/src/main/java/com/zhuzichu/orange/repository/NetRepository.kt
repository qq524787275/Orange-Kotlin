package com.zhuzichu.orange.repository

import com.zhuzichu.base.entity.BaseRes
import com.zhuzichu.orange.entity.BeanToken
import io.reactivex.Flowable

/**
 * desc:  <br/>
 * time: 2019/10/8 15:46 <br/>
 * author: Coffee <br/>
 * since V 1.2 <br/>
 */
interface NetRepository {

    fun login(
        username: String,
        password: String
    ): Flowable<BaseRes<BeanToken>>

}
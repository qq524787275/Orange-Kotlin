package com.zhuzichu.base.entity

import java.io.Serializable


data class BaseRes<T>(
    val code: Int,
    val msg: String,
    val data: T
) : Serializable
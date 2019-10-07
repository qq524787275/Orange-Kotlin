package com.zhuzichu.base.http.exception

class ResponseThrowable(var msg: String? = null, var code: Int? = null) : RuntimeException()
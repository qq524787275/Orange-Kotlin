package com.zhuzichu.base.http.converter

import com.google.gson.Gson
import com.zhuzichu.base.entity.BaseRes
import com.zhuzichu.base.http.exception.HttpCode
import com.zhuzichu.base.http.exception.ResponseThrowable
import okhttp3.ResponseBody
import retrofit2.Converter
import java.lang.reflect.Type

class GsonResponseBodyConverter<T>(
    private val gson: Gson,
    private var type: Type
) : Converter<ResponseBody, T> {

    override fun convert(value: ResponseBody): T {
        val json = value.string()
        value.use {
            verify(json)
            return gson.fromJson(json, type)
        }
    }

    private fun verify(json: String?) {
        val result = gson.fromJson(json, BaseRes::class.java)
        val code = result.code
        if (code != HttpCode.SUCCESS.code) {
            when (code) {
                HttpCode.PARSE_ERROR.code -> {
                    throw ResponseThrowable(result.msg, code)
                }
                else -> {
                    throw ResponseThrowable(result.msg, code)
                }
            }
        }
    }
}
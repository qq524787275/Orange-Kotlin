package com.zhuzichu.base.http.converter

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.zhuzichu.base.ext.toCast
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class GsonConverterFactory(private val gson: Gson) : Converter.Factory() {

    companion object {
        fun create(): GsonConverterFactory = create(Gson())

        fun create(gson: Gson): GsonConverterFactory = GsonConverterFactory(gson)
    }

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *>? {
        return GsonResponseBodyConverter<Any>(gson, type)
    }

    override fun requestBodyConverter(
        type: Type,
        parameterAnnotations: Array<Annotation>,
        methodAnnotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<*, RequestBody>? {
        val adapter = gson.getAdapter(TypeToken.get(type))
        return GsonRequestBodyConverter<Any>(gson, adapter.toCast())
    }

}
package com.zhuzichu.orange.repository

import com.zhuzichu.base.http.RetrofitManager

private const val APP_URL = "http://47.111.70.169:8011"

interface IService {
    fun getAppService(isEncrypt: Boolean = true, isJson: Boolean = true): AppService {
        return RetrofitManager.getRetrofit(baseUrl = APP_URL, isEncrypt = isEncrypt, isJson = isJson)
            .create(AppService::class.java)
    }
}
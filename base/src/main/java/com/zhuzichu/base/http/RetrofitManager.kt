package com.zhuzichu.base.http

import android.os.Build
import androidx.annotation.NonNull
import androidx.collection.SimpleArrayMap
import com.zhuzichu.base.common.prefs.UserStorage
import com.zhuzichu.base.entity.HeaderParams
import com.zhuzichu.base.ext.Convert
import com.zhuzichu.base.ext.encryptPolicy
import com.zhuzichu.base.ext.getVersionCode
import com.zhuzichu.base.ext.getVersionName
import com.zhuzichu.base.global.CacheGlobal
import com.zhuzichu.base.http.converter.GsonConverterFactory
import com.zhuzichu.base.http.interceptor.HttpLoggingInterceptor
import okhttp3.*
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import java.util.logging.Level

object RetrofitManager {

    private const val HTTP_TIME_OUT = 32 * 1000

    private const val HTTP_MAX_CACHE_SIZE = 32 * 1024 * 1024L

    private val retrofitMap = SimpleArrayMap<String, Retrofit>()

    private val userStorage: UserStorage by lazy { UserStorage() }

    private fun createRetrofit(
        @NonNull baseUrl: String, isJson: Boolean = true,
        isEncrypt: Boolean = false
    ) {
        val timeOut = HTTP_TIME_OUT
        val cache = Cache(
            CacheGlobal.getHttpCacheDir(),
            HTTP_MAX_CACHE_SIZE
        )

        val loggingInterceptor = HttpLoggingInterceptor("Orange")
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY)
        loggingInterceptor.setColorLevel(Level.INFO)

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(timeOut.toLong(), TimeUnit.MILLISECONDS)
            .addInterceptor(BaseInterceptor(isEncrypt))
            .addNetworkInterceptor(NetworkBaseInterceptor())
            .addInterceptor(loggingInterceptor)
            .cache(cache)
            .build()

        val builder = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)

        if (isJson) {
            builder.addConverterFactory(GsonConverterFactory.create())
        } else {
            builder.addConverterFactory(ScalarsConverterFactory.create())
        }

        retrofitMap.put("$baseUrl-$isJson-$isEncrypt", builder.build())
    }

    fun getRetrofit(baseUrl: String, isJson: Boolean = true, isEncrypt: Boolean = true): Retrofit {
        val key = "$baseUrl-$isJson-$isEncrypt"
        if (!retrofitMap.containsKey(key)) {
            createRetrofit(baseUrl, isJson, isEncrypt)
        }
        return retrofitMap[key]!!
    }


    /**
     * 拦截器
     */
    private class BaseInterceptor(val encrypt: Boolean) : Interceptor {
        @Throws(IOException::class)
        override fun intercept(@NonNull chain: Interceptor.Chain): Response {
            var request = chain.request()
            if (request.method() == "POST" && encrypt) {
                val body = request.body()
                if (body is FormBody) {
                    val jsonObject = JSONObject()
                    //将以前的参数添加
                    for (i in 0 until body.size()) {
                        jsonObject.put(body.encodedName(i), body.encodedValue(i))
                    }
                    val json = jsonObject.toString()
                    request = request.newBuilder().post(
                        FormBody.create(
                            MediaType.parse("application/json"),
                            encryptPolicy(json)
                        )
                    ).build()
                }
            }
            val headerParams = HeaderParams()
                .apply {
                    device = Build.MODEL
                    platform = "android"
                    versionCode = getVersionCode()
                    versionName = getVersionName()
                }
            val policy = encryptPolicy(Convert.toJson(headerParams))
            val builder = request.newBuilder()
                .addHeader("orange", policy)
            userStorage.token?.let {
                builder.addHeader("token", it)
            }
            return chain.proceed(builder.build())
        }
    }

    /**
     * 网络请求拦截器
     */
    private class NetworkBaseInterceptor : Interceptor {
        @Throws(IOException::class)
        override fun intercept(@NonNull chain: Interceptor.Chain): Response {
            val request = chain.request()
            return chain.proceed(request)
        }
    }
}
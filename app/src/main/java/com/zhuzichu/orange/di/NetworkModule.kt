package com.zhuzichu.orange.di

import android.os.Build
import com.zhuzichu.base.common.prefs.UserStorage
import com.zhuzichu.base.entity.HeaderParams
import com.zhuzichu.base.ext.Convert
import com.zhuzichu.base.ext.encryptPolicy
import com.zhuzichu.base.ext.getVersionCode
import com.zhuzichu.base.ext.getVersionName
import com.zhuzichu.base.global.CacheGlobal
import com.zhuzichu.base.http.converter.GsonConverterFactory
import com.zhuzichu.orange.BuildConfig
import com.zhuzichu.orange.repository.remote.RemoteRepository
import com.zhuzichu.orange.repository.remote.RemoteRepositoryImpl
import dagger.Module
import dagger.Provides
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesRepository(@Named("AppRetrofit") appRetrofit: Retrofit): RemoteRepository {
        return RemoteRepositoryImpl(appRetrofit)
    }

    @Provides
    @Singleton
    @Named("AppRetrofit")
    fun providesAppRetrofit(@Named("NetworkInterceptor") networkInterceptor: Interceptor): Retrofit {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val cache = Cache(
            CacheGlobal.getHttpCacheDir(),
            32 * 1024 * 1024L
        )

        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(32 * 1000, TimeUnit.MILLISECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(networkInterceptor)
            .cache(cache)
            .build()

        val builder = Retrofit.Builder()
            .baseUrl(BuildConfig.HOST_APP2)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)

        builder.addConverterFactory(GsonConverterFactory.create())
        return builder.build()
    }

    @Provides
    @Singleton
    @Named("NetworkInterceptor")
    fun providesNetworkInterceptor(userStorage: UserStorage): Interceptor {
        return Interceptor {
            var request = it.request()
            if (request.method() == "POST") {
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
            userStorage.token?.let { token ->
                builder.addHeader("token", token)
            }
            it.proceed(builder.build())
        }
    }
}
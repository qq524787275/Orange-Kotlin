package com.zhuzichu.orange.di

import android.content.Context
import com.zhuzichu.orange.ApplicationOrange
import com.zhuzichu.orange.manager.AccountManager
import com.zhuzichu.orange.repository.NetRepository
import com.zhuzichu.orange.repository.NetRepositoryImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {
    @Provides
    fun provideContext(application: ApplicationOrange): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun providesNetRepository(): NetRepository = NetRepositoryImpl()

    @Singleton
    @Provides
    fun providesAccountManager(): AccountManager = AccountManager()
}
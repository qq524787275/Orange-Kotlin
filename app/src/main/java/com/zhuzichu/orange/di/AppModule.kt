package com.zhuzichu.orange.di

import android.content.Context
import com.zhuzichu.base.common.prefs.UserStorage
import com.zhuzichu.base.notify.NotifyManager
import com.zhuzichu.orange.ApplicationOrange
import com.zhuzichu.orange.manager.AccountManager
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
    fun providesAccountManager(): AccountManager = AccountManager()

    @Singleton
    @Provides
    fun providesNotifyManager(context: Context): NotifyManager = NotifyManager(context)


    @Singleton
    @Provides
    fun providesUserStorage(): UserStorage = UserStorage()

}
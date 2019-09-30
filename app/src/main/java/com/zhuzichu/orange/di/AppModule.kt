package com.zhuzichu.orange.di

import android.content.Context
import com.zhuzichu.orange.ApplicationOrange
import dagger.Module
import dagger.Provides

@Module
class AppModule {
    @Provides
    fun provideContext(application: ApplicationOrange): Context {
        return application.applicationContext
    }
}
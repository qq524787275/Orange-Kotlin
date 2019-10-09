package com.zhuzichu.orange.di

import com.zhuzichu.base.di.ViewModelModule
import com.zhuzichu.orange.ApplicationOrange
import com.zhuzichu.orange.ui.main.module.ModuleMain
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ViewModelModule::class,
        AppModule::class,
        ActivityBindingModule::class,
        NetworkModule::class
    ]
)

interface AppComponent : AndroidInjector<ApplicationOrange> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: ApplicationOrange): AppComponent
    }
}
package com.zhuzichu.orange

import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy.Builder
import androidx.appcompat.app.AppCompatDelegate
import com.zhuzichu.base.common.prefs.UserStorage

import com.zhuzichu.base.global.AppGlobal
import com.zhuzichu.orange.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

import io.reactivex.plugins.RxJavaPlugins.setErrorHandler
import javax.inject.Inject

/**
 * @author Administrator
 */
class ApplicationOrange : DaggerApplication() {

    @Inject
    lateinit var userStorage: UserStorage

    override fun onCreate() {
        if (BuildConfig.DEBUG) {
        }
        super.onCreate()
        AppGlobal.init(this)
        setErrorHandler {

        }
        AppCompatDelegate.setDefaultNightMode(userStorage.uiMode)
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.factory().create(this)
    }

    private fun enableStrictMode() {
        StrictMode.setThreadPolicy(
            Builder()
                .detectDiskReads()
                .detectDiskWrites()
                .detectNetwork()
                .penaltyLog()
                .build()
        )
    }
}
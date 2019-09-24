package com.zhuzichu.orange

import android.app.Application

import androidx.appcompat.app.AppCompatDelegate
import com.zhuzichu.base.common.preference.UserPreference

import com.zhuzichu.base.global.AppGlobal

import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.plugins.RxJavaPlugins.setErrorHandler
import me.jessyan.autosize.AutoSize
import me.jessyan.autosize.AutoSizeConfig

/**
 * @author Administrator
 */
class ApplicationOrange : Application() {

    private val userPreference: UserPreference by lazy { UserPreference() }

    override fun onCreate() {
        super.onCreate()
        AppGlobal.init(this)
        initAutoSize()
        setErrorHandler {

        }
        AppCompatDelegate.setDefaultNightMode(userPreference.uiMode)
    }

    private fun initAutoSize() {
        AutoSizeConfig.getInstance()
            .setCustomFragment(false)
            .setLog(false)
            .setUseDeviceSize(false)
            .setBaseOnWidth(true)
        AutoSize.initCompatMultiProcess(this)
    }

}
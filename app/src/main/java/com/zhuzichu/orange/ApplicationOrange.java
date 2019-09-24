package com.zhuzichu.orange;

import android.app.Application;
import android.content.res.Configuration;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDelegate;

import com.zhuzichu.base.ext.ToastExtKt;
import com.zhuzichu.base.global.AppGlobal;

import io.reactivex.plugins.RxJavaPlugins;
import me.jessyan.autosize.AutoSize;
import me.jessyan.autosize.AutoSizeConfig;

/**
 * @author Administrator
 */
public class ApplicationOrange extends Application {



    @Override
    public void onCreate() {
        super.onCreate();
        AppGlobal.INSTANCE.init(this);
        initAutoSize();
        RxJavaPlugins.setErrorHandler(throwable -> {

        });
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

    }

    private void initAutoSize() {
        AutoSizeConfig.getInstance()
                .setCustomFragment(false)
                .setLog(false)
                .setUseDeviceSize(false)
                .setBaseOnWidth(true);
        AutoSize.initCompatMultiProcess(this);
    }

}
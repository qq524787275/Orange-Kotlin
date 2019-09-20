package com.zhuzichu.orange;

import android.app.Application;

import com.zhuzichu.base.global.AppGlobal;

import io.reactivex.functions.Consumer;
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

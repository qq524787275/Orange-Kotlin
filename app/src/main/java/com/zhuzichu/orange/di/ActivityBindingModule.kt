package com.zhuzichu.orange.di

import com.zhuzichu.base.di.ActivityScoped
import com.zhuzichu.orange.ui.ActivityMain
import com.zhuzichu.orange.ui.account.ActivityAccount
import com.zhuzichu.orange.ui.category.module.ModuleCategory
import com.zhuzichu.orange.ui.find.module.ModuleFind
import com.zhuzichu.orange.ui.home.module.ModuleHome
import com.zhuzichu.orange.ui.main.module.ModuleMain
import com.zhuzichu.orange.ui.account.login.module.ModuleLogin
import com.zhuzichu.orange.ui.account.register.module.ModuleRegister
import com.zhuzichu.orange.ui.mine.main.module.ModuleMine
import com.zhuzichu.orange.ui.mine.setting.languages.module.ModuleLanguages
import com.zhuzichu.orange.ui.mine.setting.main.module.ModuleSetting
import com.zhuzichu.orange.ui.mine.setting.theme.module.ModuleTheme
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            //fragments
            ModuleMain::class,
            ModuleHome::class,
            ModuleCategory::class,
            ModuleFind::class,
            ModuleMine::class,
            ModuleSetting::class,
            ModuleLanguages::class,
            ModuleTheme::class
            // other

        ]
    )
    internal abstract fun mainActivity(): ActivityMain

    @ActivityScoped
    @ContributesAndroidInjector(
        modules = [
            ModuleLogin::class,
            ModuleRegister::class
        ]
    )
    internal abstract fun accountActivity(): ActivityAccount

}
package com.zhuzichu.orange.ui.mine.setting.main.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.base.di.ViewModelKey
import com.zhuzichu.orange.ui.mine.setting.main.fragment.FragmentSetting
import com.zhuzichu.orange.ui.mine.setting.main.viewmodel.ViewModelSetting
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleSetting {
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentSetting(): FragmentSetting


    @Binds
    @IntoMap
    @ViewModelKey(ViewModelSetting::class)
    abstract fun bindViewModelSetting(viewModel: ViewModelSetting): ViewModel
}
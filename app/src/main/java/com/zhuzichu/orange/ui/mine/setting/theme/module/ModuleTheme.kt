package com.zhuzichu.orange.ui.mine.setting.theme.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.base.di.ViewModelKey
import com.zhuzichu.orange.ui.mine.setting.theme.fragment.FragmentTheme
import com.zhuzichu.orange.ui.mine.setting.theme.viewmodel.ViewModelTheme
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleTheme {
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentTheme(): FragmentTheme


    @Binds
    @IntoMap
    @ViewModelKey(ViewModelTheme::class)
    abstract fun bindViewModelTheme(viewModel: ViewModelTheme): ViewModel
}
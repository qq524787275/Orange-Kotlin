package com.zhuzichu.orange.ui.mine.Languages.languages.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.base.di.ViewModelKey
import com.zhuzichu.orange.ui.mine.setting.languages.fragment.FragmentLanguages
import com.zhuzichu.orange.ui.mine.setting.languages.viewmodel.ViewModelLanguages
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleLanguages {
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentLanguages(): FragmentLanguages


    @Binds
    @IntoMap
    @ViewModelKey(ViewModelLanguages::class)
    abstract fun bindViewModelLanguages(viewModel: ViewModelLanguages): ViewModel
}
package com.zhuzichu.orange.ui.main.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.base.di.ViewModelKey
import com.zhuzichu.orange.ui.main.fragment.FragmentMain
import com.zhuzichu.orange.ui.main.viewmodel.ViewModelMain
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleMain {

    @ContributesAndroidInjector
    internal abstract fun contributeFragmentMain(): FragmentMain

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelMain::class)
    abstract fun bindViewModelMain(viewModel: ViewModelMain): ViewModel

}
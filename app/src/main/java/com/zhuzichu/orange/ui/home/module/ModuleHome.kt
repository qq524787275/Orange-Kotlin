package com.zhuzichu.orange.ui.home.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.base.di.ChildFragmentScoped
import com.zhuzichu.base.di.ViewModelKey
import com.zhuzichu.orange.ui.home.fragment.FragmentHome
import com.zhuzichu.orange.ui.home.viewmodel.ViewModelHome
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap


@Module
internal abstract class ModuleHome {

    @ContributesAndroidInjector
    @ChildFragmentScoped
    internal abstract fun contributeFragmentHome(): FragmentHome

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelHome::class)
    abstract fun bindViewModelHome(viewModel: ViewModelHome): ViewModel

}
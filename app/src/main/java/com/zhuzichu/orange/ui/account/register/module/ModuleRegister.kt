package com.zhuzichu.orange.ui.account.register.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.base.di.ViewModelKey
import com.zhuzichu.orange.ui.account.register.fragment.FragmentRegister
import com.zhuzichu.orange.ui.account.register.viewmodel.ViewModelRegister
import com.zhuzichu.orange.ui.home.viewmodel.ViewModelHome
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
abstract class ModuleRegister {

    @ContributesAndroidInjector
    internal abstract fun contributeFragmentRegister(): FragmentRegister

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelRegister::class)
    abstract fun bindViewModelRegister(viewModel: ViewModelRegister): ViewModel

}
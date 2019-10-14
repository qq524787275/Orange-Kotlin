package com.zhuzichu.orange.ui.account.login.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.base.di.FragmentScoped
import com.zhuzichu.base.di.ViewModelKey
import com.zhuzichu.orange.ui.account.login.fragment.FragmentLogin
import com.zhuzichu.orange.ui.account.login.viewmodel.ViewModelLogin
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleLogin {

    @FragmentScoped
    @ContributesAndroidInjector
    internal abstract fun contributeFragmentLogin(): FragmentLogin

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelLogin::class)
    abstract fun bindViewModelLogin(viewModel: ViewModelLogin): ViewModel
}
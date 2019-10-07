package com.zhuzichu.orange.ui.mine.login.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.base.di.ViewModelKey
import com.zhuzichu.orange.ui.mine.login.fragment.FragmentLogin
import com.zhuzichu.orange.ui.mine.login.viewmodel.ViewModelLogin
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleLogin {

    @ContributesAndroidInjector
    internal abstract fun contributeFragmentLogin(): FragmentLogin

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelLogin::class)
    abstract fun bindViewModelLogin(viewModel: ViewModelLogin): ViewModel
}

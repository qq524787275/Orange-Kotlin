package com.zhuzichu.orange.ui.find.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.base.di.ChildFragmentScoped
import com.zhuzichu.base.di.ViewModelKey
import com.zhuzichu.orange.ui.find.fragment.FragmentFind
import com.zhuzichu.orange.ui.find.viewmodel.ViewModelFind
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleFind {

    @ContributesAndroidInjector
    @ChildFragmentScoped
    internal abstract fun contributeFragmentFind(): FragmentFind

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelFind::class)
    abstract fun bindViewModelFind(viewModel: ViewModelFind): ViewModel

}
package com.zhuzichu.orange.ui.mine.main.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.base.di.ChildFragmentScoped
import com.zhuzichu.base.di.FragmentScoped
import com.zhuzichu.base.di.ViewModelKey
import com.zhuzichu.orange.ui.mine.main.fragment.FragmentMine
import com.zhuzichu.orange.ui.mine.main.viewmodel.ViewModelMine
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleMine {

    @ContributesAndroidInjector
    @ChildFragmentScoped
    internal abstract fun contributeFragmentMine(): FragmentMine

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelMine::class)
    abstract fun bindViewModelCategory(viewModel: ViewModelMine): ViewModel

}
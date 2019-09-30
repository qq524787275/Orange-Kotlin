package com.zhuzichu.orange.ui.category.module

import androidx.lifecycle.ViewModel
import com.zhuzichu.base.di.ViewModelKey
import com.zhuzichu.orange.ui.category.fragment.FragmentCategory
import com.zhuzichu.orange.ui.category.viewmodel.ViewModelCategory
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

@Module
internal abstract class ModuleCategory {

    @ContributesAndroidInjector
    internal abstract fun contributeFragmentCategory(): FragmentCategory

    @Binds
    @IntoMap
    @ViewModelKey(ViewModelCategory::class)
    abstract fun bindViewModelCategory(viewModel: ViewModelCategory): ViewModel

}
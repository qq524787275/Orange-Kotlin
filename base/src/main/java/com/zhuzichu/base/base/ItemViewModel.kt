package com.zhuzichu.base.base

import androidx.annotation.NonNull
import androidx.lifecycle.AndroidViewModel

open class ItemViewModel<TViewModel : AndroidViewModel>(@param:NonNull protected var viewModel: TViewModel)
package com.zhuzichu.base.base

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.zhuzichu.base.ext.toCast
import me.yokeyword.fragmentation.SupportFragmentDelegate
import java.lang.reflect.ParameterizedType

abstract class BaseNavFragment<TParams : BaseParams, TBinding : ViewDataBinding, TViewModel : BaseViewModel> :
    Fragment(), IBaseFragment {

    lateinit var binding: TBinding
    lateinit var viewModel: TViewModel
    lateinit var params: TParams
    lateinit var contentView: View
    lateinit var activityCtx: Activity
    val lifecycleProvider by lazy { AndroidLifecycleScopeProvider.from(this) }

    abstract fun setLayoutId(): Int
    abstract fun bindVariableId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val type = this::class.java.genericSuperclass
        if (type is ParameterizedType)
            viewModel = ViewModelProvider(this).get(type.actualTypeArguments[2].toCast())
        lifecycle.addObserver(viewModel)
        contentView = inflater.inflate(setLayoutId(), container, false)
        binding = DataBindingUtil.bind<ViewDataBinding>(contentView).toCast()
        binding.setVariable(bindVariableId(), viewModel)
        arguments?.let {
            params = it.getParcelable<BaseParams>(BaseConst.PARAMS).toCast()
        }
        viewModel.injectFragment(this)
        viewModel.injectActivity(activityCtx)
        viewModel.injectLifecycleProvider(lifecycleProvider)
        return contentView.also {
            binding.lifecycleOwner = this
            binding.executePendingBindings()
        }
    }

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        activityCtx = activity
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initVariable()
        initView()
        initViewObservable()
    }
}
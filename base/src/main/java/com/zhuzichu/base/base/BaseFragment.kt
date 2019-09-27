package com.zhuzichu.base.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.navOptions
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.zhuzichu.base.R
import com.zhuzichu.base.ext.toCast
import java.lang.reflect.ParameterizedType

/**
 * desc:  <br/>
 * time: 2019/9/17 17:35 <br/>
 * author: Coffee <br/>
 * since V 1.2 <br/>
 */

abstract class BaseFragment<TParams : BaseParams, TBinding : ViewDataBinding, TViewModel : BaseViewModel> :
    Fragment(),
    IBaseFragment {
    lateinit var binding: TBinding
    lateinit var viewModel: TViewModel
    lateinit var params: TParams
    lateinit var contentView: View
    lateinit var activityCtx: Activity
    private val lifecycleProvider by lazy { AndroidLifecycleScopeProvider.from(this) }
    private val navController by lazy { activityCtx.findNavController(R.id.delegate_container) }

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
            params = it.getParcelable<BaseParams>(Const.PARAMS).toCast()
        }
        viewModel.injectFragment(this)
        viewModel.injectActivity(activityCtx)
        viewModel.injectLifecycleProvider(lifecycleProvider)
        return contentView.also {
            binding.lifecycleOwner = this
            binding.executePendingBindings()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registUIChangeLiveDataCallback()
        initVariable()
        initView()
        initViewObservable()
        viewModel.initData()
    }

    private fun registUIChangeLiveDataCallback() {
        viewModel.uc.startActivityEvent.observe(this, Observer {
            val clz = it[Const.CLASS] as Class<*>
            val params = it[Const.PARAMS] as BaseParams
            val isPop = it[Const.POP] as Boolean
            val requestCode = it[Const.REQUEST_CODE] as Int
            val options = it[Const.OPTIONS] as Bundle
            val intent = Intent(activityCtx, clz)
            intent.putExtra(Const.PARAMS, params)
            startActivityForResult(intent, requestCode, options)
            if (isPop) {
                activityCtx.finish()
            }
        })

        viewModel.uc.startFragmentEvent.observe(this, Observer {
            val actionId = it[Const.ACTION_ID] as Int
            val params = it[Const.PARAMS] as BaseParams
            navController.navigate(actionId, bundleOf(Const.PARAMS to params), navOptions {
                anim {
                    enter = R.anim.slide_in_right // 进入页面动画
                    exit = R.anim.slide_out_left
                    popEnter = R.anim.slide_in_left  // 弹出栈动画
                    popExit = R.anim.slide_out_right
                }
            })
        })

        viewModel.uc.onBackPressedEvent.observe(this, Observer {
            activityCtx.onBackPressed()
        })
    }


    @Suppress("DEPRECATION")
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        activityCtx = activity
    }


    override fun onDestroy() {
        super.onDestroy()
        if (::viewModel.isInitialized)
            lifecycle.removeObserver(viewModel)
        if (::binding.isInitialized)
            binding.unbind()
    }

}
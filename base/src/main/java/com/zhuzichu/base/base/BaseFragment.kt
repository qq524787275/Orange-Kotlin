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
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.navOptions
import com.zhuzichu.base.R
import com.zhuzichu.base.ext.toCast
import dagger.android.support.DaggerFragment
import java.lang.reflect.ParameterizedType
import javax.inject.Inject

abstract class BaseFragment<TParams : BaseParams, TBinding : ViewDataBinding, TViewModel : BaseViewModel> :
    DaggerFragment(),
    IBaseFragment {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: TBinding
    lateinit var viewModel: TViewModel
    lateinit var params: TParams

    lateinit var activityCtx: Activity

    private val navController by lazy { activityCtx.findNavController(R.id.delegate_container) }
    val visibleDelegate by lazy { VisibleDelegate(this) }

    abstract fun setLayoutId(): Int
    abstract fun bindVariableId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        visibleDelegate.onCreate(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        visibleDelegate.onSaveInstanceState(outState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        visibleDelegate.onActivityCreated(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val type = this::class.java.genericSuperclass
        if (type is ParameterizedType) {
            viewModel = ViewModelProvider(this, viewModelFactory)
                .get(type.actualTypeArguments[2].toCast())
        }
        lifecycle.addObserver(viewModel)
        val rootView = inflater.inflate(setLayoutId(), container, false)
        binding = DataBindingUtil.bind<ViewDataBinding>(rootView).toCast()
        binding.setVariable(bindVariableId(), viewModel)
        arguments?.let {
            params = it.getParcelable<BaseParams>(Const.PARAMS).toCast()
        }
        return rootView.also {
            binding.lifecycleOwner = this
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registUIChangeLiveDataCallback()
        initVariable()
        initView()
        initViewObservable()
        initData()
    }

    override fun onResume() {
        super.onResume()
        visibleDelegate.onResume()
    }

    override fun onPause() {
        super.onPause()
        visibleDelegate.onPause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        visibleDelegate.onDestroyView()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        visibleDelegate.onHiddenChanged(hidden)
    }

    @Suppress("DEPRECATION")
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        visibleDelegate.setUserVisibleHint(isVisibleToUser)
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

    override fun isSupportVisible(): Boolean {
        return visibleDelegate.isSupportVisible
    }
}
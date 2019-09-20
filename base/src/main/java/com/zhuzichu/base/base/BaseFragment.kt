package com.zhuzichu.base.base

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentationMagician
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider
import com.zhuzichu.base.ext.toCast
import me.yokeyword.fragmentation.ExtraTransaction
import me.yokeyword.fragmentation.ISupportFragment
import me.yokeyword.fragmentation.SupportFragmentDelegate
import me.yokeyword.fragmentation.SupportHelper
import me.yokeyword.fragmentation.anim.FragmentAnimator
import java.lang.reflect.ParameterizedType

/**
 * desc:  <br/>
 * time: 2019/9/17 17:35 <br/>
 * author: Coffee <br/>
 * since V 1.2 <br/>
 */

abstract class BaseFragment<TParams : BaseParams, TBinding : ViewDataBinding, TViewModel : BaseViewModel> :
    Fragment(),
    ISupportFragment,
    IBaseFragment {
    lateinit var binding: TBinding
    lateinit var viewModel: TViewModel
    lateinit var params: TParams
    lateinit var contentView: View
    lateinit var activityCtx: Activity
    val lifecycleProvider by lazy { AndroidLifecycleScopeProvider.from(this) }
    private val delegate by lazy { SupportFragmentDelegate(this) }

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registUIChangeLiveDataCallback()
        initVariable()
        initView()
        initViewObservable()
    }

    private fun registUIChangeLiveDataCallback() {
        viewModel.uc.startActivityEvent.observe(this, Observer {
            val clz = it[BaseConst.CLASS] as Class<*>
            val params = it[BaseConst.PARAMS] as BaseParams
            val isPop = it[BaseConst.POP] as Boolean
            val options = it[BaseConst.OPTIONS] as Bundle?
            val requestCode = it[BaseConst.REQUEST_CODE] as Int?
            val intent = Intent(activityCtx, clz)
            intent.putExtra(BaseConst.PARAMS, params)
            if (requestCode != null) {
                if (options != null) {
                    startActivityForResult(intent, requestCode, options)
                } else {
                    startActivityForResult(intent, requestCode)
                }
            } else {
                if (options != null) {
                    startActivity(intent, options)
                } else {
                    startActivity(intent)
                }
            }
            if (isPop) {
                activityCtx.finish()
            }
        })

        viewModel.uc.startFragmentEvent.observe(this, Observer {
            val fragment = it[BaseConst.FRAGMENT] as BaseFragment<*, *, *>
            val params = it[BaseConst.PARAMS] as BaseParams
            fragment.arguments?.putParcelable(BaseConst.PARAMS, params)
            val launchMode = it[BaseConst.FRAGMENT_LAUNCHMODE] as Int
            getSuperTopFragment().start(fragment, launchMode)
        })

        viewModel.uc.onBackPressedEvent.observe(this, Observer {
            activityCtx.onBackPressed()
        })
    }

    override fun getSupportDelegate(): SupportFragmentDelegate {
        return delegate
    }

    override fun extraTransaction(): ExtraTransaction {
        return delegate.extraTransaction()
    }

    @Suppress("DEPRECATION")
    override fun onAttach(activity: Activity) {
        super.onAttach(activity)
        delegate.onAttach(activity)
        activityCtx = delegate.activity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        delegate.onCreate(savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        delegate.onActivityCreated(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        delegate.onSaveInstanceState(outState)
    }

    override fun onResume() {
        super.onResume()
        delegate.onResume()
    }

    override fun onPause() {
        super.onPause()
        delegate.onPause()
    }

    override fun onDestroyView() {
        delegate.onDestroyView()
        super.onDestroyView()
    }

    override fun onDestroy() {
        delegate.onDestroy()
        super.onDestroy()
        if (::viewModel.isInitialized)
            lifecycle.removeObserver(viewModel)
        if (::binding.isInitialized)
            binding.unbind()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        delegate.onHiddenChanged(hidden)
    }

    @Suppress("DEPRECATION")
    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        delegate.setUserVisibleHint(isVisibleToUser)
    }

    override fun enqueueAction(runnable: Runnable) {
        delegate.post(runnable)
    }

    override fun post(runnable: Runnable) {
        delegate.post(runnable)
    }

    override fun onEnterAnimationEnd(savedInstanceState: Bundle?) {
        delegate.onEnterAnimationEnd(savedInstanceState)
    }

    override fun onLazyInitView(savedInstanceState: Bundle?) {
        delegate.onLazyInitView(savedInstanceState)
    }

    override fun onSupportVisible() {
        delegate.onSupportVisible()
    }

    override fun onSupportInvisible() {
        delegate.onSupportInvisible()
    }

    override fun isSupportVisible(): Boolean {
        return delegate.isSupportVisible
    }

    override fun onCreateFragmentAnimator(): FragmentAnimator {
        return delegate.onCreateFragmentAnimator()
    }

    override fun getFragmentAnimator(): FragmentAnimator {
        return delegate.fragmentAnimator
    }

    override fun setFragmentAnimator(fragmentAnimator: FragmentAnimator) {
        delegate.fragmentAnimator = fragmentAnimator
    }

    override fun onBackPressedSupport(): Boolean {
        return delegate.onBackPressedSupport()
    }

    override fun setFragmentResult(resultCode: Int, bundle: Bundle) {
        delegate.setFragmentResult(resultCode, bundle)
    }

    override fun onFragmentResult(requestCode: Int, resultCode: Int, data: Bundle) {
        delegate.onFragmentResult(requestCode, resultCode, data)
    }

    override fun onNewBundle(args: Bundle) {
        delegate.onNewBundle(args)
    }

    override fun putNewBundle(newBundle: Bundle) {
        delegate.putNewBundle(newBundle)
    }

    protected fun hideSoftInput() {
        delegate.hideSoftInput()
    }

    protected fun showSoftInput(view: View) {
        delegate.showSoftInput(view)
    }

    fun loadRootFragment(containerId: Int, toFragment: ISupportFragment) {
        delegate.loadRootFragment(containerId, toFragment)
    }

    fun loadRootFragment(
        containerId: Int,
        toFragment: ISupportFragment,
        addToBackStack: Boolean,
        allowAnim: Boolean
    ) {
        delegate.loadRootFragment(containerId, toFragment, addToBackStack, allowAnim)
    }

    fun loadMultipleRootFragment(
        containerId: Int,
        showPosition: Int,
        vararg toFragments: ISupportFragment
    ) {
        delegate.loadMultipleRootFragment(containerId, showPosition, *toFragments)
    }

    fun showHideFragment(showFragment: ISupportFragment) {
        delegate.showHideFragment(showFragment)
    }

    fun showHideFragment(showFragment: ISupportFragment, hideFragment: ISupportFragment) {
        delegate.showHideFragment(showFragment, hideFragment)
    }

    fun start(toFragment: ISupportFragment) {
        delegate.start(toFragment)
    }

    fun start(toFragment: ISupportFragment, @ISupportFragment.LaunchMode launchMode: Int) {
        delegate.start(toFragment, launchMode)
    }

    fun startForResult(toFragment: ISupportFragment, requestCode: Int) {
        delegate.startForResult(toFragment, requestCode)
    }

    fun startWithPop(toFragment: ISupportFragment) {
        delegate.startWithPop(toFragment)
    }

    fun startWithPopTo(
        toFragment: ISupportFragment,
        targetFragmentClass: Class<*>,
        includeTargetFragment: Boolean
    ) {
        delegate.startWithPopTo(toFragment, targetFragmentClass, includeTargetFragment)
    }

    fun replaceFragment(toFragment: ISupportFragment, addToBackStack: Boolean) {
        delegate.replaceFragment(toFragment, addToBackStack)
    }

    fun pop() {
        delegate.pop()
    }

    fun popChild() {
        delegate.popChild()
    }

    fun popTo(targetFragmentClass: Class<*>, includeTargetFragment: Boolean) {
        delegate.popTo(targetFragmentClass, includeTargetFragment)
    }

    fun popTo(
        targetFragmentClass: Class<*>,
        includeTargetFragment: Boolean,
        afterPopTransactionRunnable: Runnable
    ) {
        delegate.popTo(targetFragmentClass, includeTargetFragment, afterPopTransactionRunnable)
    }

    fun popTo(
        targetFragmentClass: Class<*>,
        includeTargetFragment: Boolean,
        afterPopTransactionRunnable: Runnable,
        popAnim: Int
    ) {
        delegate.popTo(
            targetFragmentClass,
            includeTargetFragment,
            afterPopTransactionRunnable,
            popAnim
        )
    }

    fun popToChild(targetFragmentClass: Class<*>, includeTargetFragment: Boolean) {
        delegate.popToChild(targetFragmentClass, includeTargetFragment)
    }

    fun popToChild(
        targetFragmentClass: Class<*>,
        includeTargetFragment: Boolean,
        afterPopTransactionRunnable: Runnable
    ) {
        delegate.popToChild(
            targetFragmentClass,
            includeTargetFragment,
            afterPopTransactionRunnable
        )
    }

    fun popToChild(
        targetFragmentClass: Class<*>,
        includeTargetFragment: Boolean,
        afterPopTransactionRunnable: Runnable,
        popAnim: Int
    ) {
        delegate.popToChild(
            targetFragmentClass,
            includeTargetFragment,
            afterPopTransactionRunnable,
            popAnim
        )
    }

    fun getTopFragment(): ISupportFragment {
        return SupportHelper.getTopFragment(fragmentManager!!)
    }

    fun getTopChildFragment(): ISupportFragment {
        return SupportHelper.getTopFragment(childFragmentManager)
    }

    fun getPreFragment(): ISupportFragment {
        return SupportHelper.getPreFragment(this)
    }

    fun <T : ISupportFragment> findFragment(fragmentClass: Class<T>): T {
        return SupportHelper.findFragment(fragmentManager!!, fragmentClass)
    }

    fun <T : ISupportFragment> findChildFragment(fragmentClass: Class<T>): T {
        return SupportHelper.findFragment(childFragmentManager, fragmentClass)
    }

    override fun onCreateAnimation(transit: Int, enter: Boolean, nextAnim: Int): Animation? {
        return delegate.onCreateAnimation(transit, enter, nextAnim)
    }

    fun getSuperTopFragment(): BaseFragment<*, *, *> {
        return FragmentationMagician.getActiveFragments((activityCtx as FragmentActivity).supportFragmentManager)[0] as BaseFragment<*, *, *>
    }
}
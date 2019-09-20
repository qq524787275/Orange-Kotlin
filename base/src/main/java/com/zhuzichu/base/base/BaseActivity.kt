package com.zhuzichu.base.base

import android.os.Bundle
import android.view.MotionEvent
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import com.zhuzichu.base.R
import me.yokeyword.fragmentation.*
import me.yokeyword.fragmentation.anim.FragmentAnimator

abstract class BaseActivity : AppCompatActivity(), ISupportActivity {
    abstract fun setRootFragment(): ISupportFragment
    private val delegate by lazy { SupportActivityDelegate(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        delegate.onCreate(savedInstanceState)
        initContainer(savedInstanceState)
    }

    private fun initContainer(savedInstanceState: Bundle?) {
        val container = FrameLayout(this)
        container.id = R.id.delegate_containe
        setContentView(container)
        if (savedInstanceState == null)
            delegate.loadRootFragment(R.id.delegate_containe, setRootFragment())
    }

    override fun getSupportDelegate(): SupportActivityDelegate {
        return delegate
    }

    override fun extraTransaction(): ExtraTransaction {
        return delegate.extraTransaction()
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        delegate.onPostCreate(savedInstanceState)
    }

    override fun onDestroy() {
        delegate.onDestroy()
        super.onDestroy()
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        return delegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev)
    }

    override fun onBackPressed() {
        delegate.onBackPressed()
    }

    override fun onBackPressedSupport() {
        delegate.onBackPressedSupport()
    }

    override fun getFragmentAnimator(): FragmentAnimator {
        return delegate.fragmentAnimator
    }

    override fun setFragmentAnimator(fragmentAnimator: FragmentAnimator) {
        delegate.fragmentAnimator = fragmentAnimator
    }

    override fun onCreateFragmentAnimator(): FragmentAnimator {
        return delegate.onCreateFragmentAnimator()
    }

    override fun post(runnable: Runnable) {
        delegate.post(runnable)
    }

    fun loadRootFragment(containerId: Int, toFragment: ISupportFragment) {
        delegate.loadRootFragment(containerId, toFragment)
    }

    fun loadRootFragment(
        containerId: Int,
        toFragment: ISupportFragment,
        addToBackStack: Boolean,
        allowAnimation: Boolean
    ) {
        delegate.loadRootFragment(containerId, toFragment, addToBackStack, allowAnimation)
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

    fun setDefaultFragmentBackground(@DrawableRes backgroundRes: Int) {
        delegate.defaultFragmentBackground = backgroundRes
    }

    fun getTopFragment(): ISupportFragment {
        return SupportHelper.getTopFragment(supportFragmentManager)
    }

    fun <T : ISupportFragment> findFragment(fragmentClass: Class<T>): T {
        return SupportHelper.findFragment(supportFragmentManager, fragmentClass)
    }
}
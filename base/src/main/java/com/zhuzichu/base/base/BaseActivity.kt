package com.zhuzichu.base.base

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.widget.FrameLayout
import androidx.activity.addCallback
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.zhuzichu.base.R
import com.zhuzichu.base.common.prefs.UserStorage
import com.zhuzichu.base.ext.hideSoftInput
import com.zhuzichu.base.ext.localeContextWrapper
import com.zhuzichu.base.ext.toast
import dagger.android.support.DaggerAppCompatActivity
import me.jessyan.autosize.AutoSize

import java.util.*


abstract class BaseActivity : DaggerAppCompatActivity() {

    abstract fun setNavGraph(): Int

    private val userStorage: UserStorage by lazy { UserStorage() }

    val navController by lazy { findNavController(R.id.delegate_container) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        delegate.onCreate(savedInstanceState)
        initContainer(savedInstanceState)

    }

    private fun initContainer(savedInstanceState: Bundle?) {
        val container = FrameLayout(this)
        container.id = R.id.delegate_container
        setContentView(container)
        if (savedInstanceState == null) {
            setContentView(container)
            val fragment = NavHostFragment.create(setNavGraph())
            supportFragmentManager.beginTransaction()
                .replace(R.id.delegate_container, fragment)
                .setPrimaryNavigationFragment(fragment)
                .commit()
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        newBase?.let {
            super.attachBaseContext(it.localeContextWrapper(Locale(userStorage.locale!!)))
        }

    }

    override fun applyOverrideConfiguration(overrideConfiguration: Configuration?) {
        //主题和语言切换产生冲突 具体详见
        // https://stackoverflow.com/questions/55265834/change-locale-not-work-after-migrate-to-androidx
        if (overrideConfiguration != null) {
            val uiMode = overrideConfiguration.uiMode
            overrideConfiguration.setTo(baseContext.resources.configuration)
            overrideConfiguration.uiMode = uiMode
        }
        super.applyOverrideConfiguration(overrideConfiguration)
    }

}
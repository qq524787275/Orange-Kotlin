package com.zhuzichu.base.base

import android.content.Context
import android.os.Bundle
import android.view.MotionEvent
import android.widget.FrameLayout
import androidx.annotation.DrawableRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.zhuzichu.base.R
import com.zhuzichu.base.common.preference.UserPreference
import me.yokeyword.fragmentation.*
import me.yokeyword.fragmentation.anim.FragmentAnimator

import java.util.*


abstract class BaseActivity : AppCompatActivity() {

    abstract fun setNavGraph(): Int
    private val userPreference: UserPreference by lazy { UserPreference() }

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
            val transcation = supportFragmentManager.beginTransaction()
            transcation.add(R.id.delegate_container, fragment)
            transcation.commit()
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        newBase?.let {
            val locale = Locale(userPreference.local!!)
            val res = newBase.resources
            val config = res.configuration
            config.setLocale(locale) // getLocale() should return a Locale
            val newContext = newBase.createConfigurationContext(config)
            super.attachBaseContext(newContext)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.delegate_container).navigateUp()
    }
}
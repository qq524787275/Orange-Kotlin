package com.zhuzichu.base.base

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.zhuzichu.base.R

abstract class BaseNavActivity : AppCompatActivity() {

    abstract fun setNavGraph(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initContainer(savedInstanceState)
    }

    private fun initContainer(savedInstanceState: Bundle?) {
        val container = FrameLayout(this)
        container.id = R.id.delegate_container
        if (savedInstanceState == null) {
            setContentView(container)
            val fragment = NavHostFragment.create(setNavGraph())
            val transcation = supportFragmentManager.beginTransaction()
            transcation.add(R.id.delegate_container, fragment)
            transcation.commit()
        }
    }
}
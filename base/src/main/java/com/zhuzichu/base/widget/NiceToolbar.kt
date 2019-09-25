package com.zhuzichu.base.widget

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import com.zhuzichu.base.R
import com.zhuzichu.base.ext.dp2px

class NiceToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val root: View?
    private val titleView: TextView?
    private val navigationTextView: TextView?
    private val navigationIconView: ImageView?

    var titleText: String? = null
        set(value) {
            value?.let {
                field = value
                titleView?.text = field
            }
        }

    var navigationText: String? = null
        set(value) {
            value?.let {
                field = value
                navigationTextView?.text = field
            }
        }

    private var navigationDrawable: Drawable? = null
        set(value) {
            value?.let {
                field = value
                navigationIconView?.setImageDrawable(it)
            }
        }

    var navigationIcon: Int? = null
        set(value) {
            value?.let {
                field = value
                navigationDrawable = AppCompatResources.getDrawable(context, it)
            }
        }

    var onOnClickNavigationListener: OnClickListener? = null
        set(value) {
            value?.let {
                field = value
                root?.findViewById<View>(R.id.navigation_layout)?.setOnClickListener(it)
            }
        }

    init {
        val arr = context.obtainStyledAttributes(attrs, R.styleable.NiceToolbar, 0, 0)
        titleText = arr.getString(R.styleable.NiceToolbar_toolbar_title)
        navigationText = arr.getString(R.styleable.NiceToolbar_toolbar_navigation_text)
        navigationDrawable = arr.getDrawable(R.styleable.NiceToolbar_toolbar_navigation_icon)
        arr.recycle()
        root = LayoutInflater.from(context)
            .inflate(R.layout.layout_toolbar, this, true)

        titleView = findViewById(R.id.title)
        navigationTextView = findViewById(R.id.navigation_text)
        navigationIconView = findViewById(R.id.navigation_icon)

        this.titleText = titleText
        this.navigationText = navigationText
        this.navigationDrawable = navigationDrawable
    }

}
package com.zhuzichu.base.widget

import android.os.Parcelable
import com.zhuzichu.base.R
import kotlinx.android.parcel.Parcelize
import me.yokeyword.fragmentation.anim.FragmentAnimator

@Parcelize
class FadeAnimator : FragmentAnimator(), Parcelable {
    init {
        enter = R.anim.fade_fragment_enter
        exit = R.anim.fade_fragment_exit
        popEnter = R.anim.fade_fragment_pop_enter
        popExit = R.anim.fade_fragment_pop_exit
    }
}
package com.zhuzichu.base.ext

import com.zhuzichu.base.global.AppGlobal

/**
 * dip转px
 *
 * @param dpValue dp值
 * @return px值
 */
fun dip2px(dpValue: Float): Int {
    return dp2px(dpValue)
}

/**
 * dp转px
 *
 * @param dpValue dp值
 * @return px值
 */
fun dp2px(dpValue: Float): Int {
    val scale = AppGlobal.context.resources.displayMetrics.density
    return (dpValue * scale + 0.5f).toInt()
}

/**
 * px转dip
 *
 * @param pxValue px值
 * @return dip值
 */
fun px2dip(pxValue: Float): Int {
    return px2dp(pxValue)
}

/**
 * px转dp
 *
 * @param pxValue px值
 * @return dp值
 */
fun px2dp(pxValue: Float): Int {
    val scale = AppGlobal.context.resources.displayMetrics.density
    return (pxValue / scale + 0.5f).toInt()
}

/**
 * sp转px
 *
 * @param spValue sp值
 * @return px值
 */
fun sp2px(spValue: Float): Int {
    val fontScale = AppGlobal.context.resources.displayMetrics.scaledDensity
    return (spValue * fontScale + 0.5f).toInt()
}

/**
 * px转sp
 *
 * @param pxValue px值
 * @return sp值
 */
fun px2sp(pxValue: Float): Int {
    val fontScale = AppGlobal.context.resources.displayMetrics.scaledDensity
    return (pxValue / fontScale + 0.5f).toInt()
}

package com.zhuzichu.base.ext

/**
 * desc:  <br/>
 * time: 2019/9/17 19:00 <br/>
 * author: Coffee <br/>
 * since V 1.2 <br/>
 */
@Suppress("UNCHECKED_CAST")
fun <T> cast(obj: Any?): T {
    return obj as T
}

@Suppress("UNCHECKED_CAST")
fun <T> Any?.toCast(): T {
    return this as T
}
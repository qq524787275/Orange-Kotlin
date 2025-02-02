package com.zhuzichu.base.binding

import com.zhuzichu.base.ext.toCast

class ResponseCommand<T, R>(
    private var execute: (() -> R)? = null,
    private var consumer: ((parameter: T) -> R)? = null,
    private var canExecute0: (() -> Boolean)? = null
) {
    fun execute(): R? {
        if (canExecute0()) {
            return execute?.invoke()
        }
        return null
    }

    fun execute(parameter: Any): R? {
        if (canExecute0()) {
            consumer?.invoke(parameter.toCast())
        }
        return null
    }

    private fun canExecute0(): Boolean {
        return canExecute0?.invoke() ?: true
    }
}
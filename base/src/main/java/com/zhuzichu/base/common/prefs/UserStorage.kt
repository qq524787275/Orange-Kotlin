package com.zhuzichu.base.common.prefs

import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate
import com.zhuzichu.base.global.AppGlobal.context

class UserStorage {

    companion object {
        const val PREFS_NAME = "user-preference"
    }

    private val prefs: Lazy<SharedPreferences> = lazy {
        // Lazy to prevent IO access to main thread.
        context.applicationContext.getSharedPreferences(
            PREFS_NAME, MODE_PRIVATE
        )
    }

    var locale by StringPreference(prefs, "zh")
    var uiMode by IntPreference(prefs, defaultValue = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
    var token by StringPreference(prefs, defaultValue = null)

}
package com.zhuzichu.base.common.preference

import android.content.Context
import android.content.SharedPreferences
import com.zhuzichu.base.global.AppGlobal
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

open class UserPreference {

    companion object {
        private const val PREFERENCE_NAME = "user-preference"
    }

    private val preferences: SharedPreferences by lazy {
        AppGlobal.context.getSharedPreferences(
            PREFERENCE_NAME,
            Context.MODE_PRIVATE
        )
    }


    var local by PreferenceDelegates.string(defaultValue = "zh")


    private object PreferenceDelegates {

        fun int(defaultValue: Int = 0) = object : ReadWriteProperty<UserPreference, Int> {

            override fun getValue(thisRef: UserPreference, property: KProperty<*>): Int {
                return thisRef.preferences.getInt(property.name, defaultValue)
            }

            override fun setValue(thisRef: UserPreference, property: KProperty<*>, value: Int) {
                thisRef.preferences.edit().putInt(property.name, value).apply()
            }
        }

        fun long(defaultValue: Long = 0L) = object : ReadWriteProperty<UserPreference, Long> {

            override fun getValue(thisRef: UserPreference, property: KProperty<*>): Long {
                return thisRef.preferences.getLong(property.name, defaultValue)
            }

            override fun setValue(thisRef: UserPreference, property: KProperty<*>, value: Long) {
                thisRef.preferences.edit().putLong(property.name, value).apply()
            }
        }

        fun boolean(defaultValue: Boolean = false) =
            object : ReadWriteProperty<UserPreference, Boolean> {
                override fun getValue(thisRef: UserPreference, property: KProperty<*>): Boolean {
                    return thisRef.preferences.getBoolean(property.name, defaultValue)
                }

                override fun setValue(
                    thisRef: UserPreference,
                    property: KProperty<*>,
                    value: Boolean
                ) {
                    thisRef.preferences.edit().putBoolean(property.name, value).apply()
                }
            }

        fun float(defaultValue: Float = 0.0f) = object : ReadWriteProperty<UserPreference, Float> {
            override fun getValue(thisRef: UserPreference, property: KProperty<*>): Float {
                return thisRef.preferences.getFloat(property.name, defaultValue)
            }

            override fun setValue(thisRef: UserPreference, property: KProperty<*>, value: Float) {
                thisRef.preferences.edit().putFloat(property.name, value).apply()
            }
        }

        fun string(defaultValue: String? = null) = object :
            ReadWriteProperty<UserPreference, String?> {
            override fun getValue(thisRef: UserPreference, property: KProperty<*>): String? {
                return thisRef.preferences.getString(property.name, defaultValue)
            }

            override fun setValue(thisRef: UserPreference, property: KProperty<*>, value: String?) {
                thisRef.preferences.edit().putString(property.name, value).apply()
            }
        }

        fun setString(defaultValue: Set<String>? = null) =
            object : ReadWriteProperty<UserPreference, Set<String>?> {
                override fun getValue(
                    thisRef: UserPreference,
                    property: KProperty<*>
                ): Set<String>? {
                    return thisRef.preferences.getStringSet(property.name, defaultValue)
                }

                override fun setValue(
                    thisRef: UserPreference,
                    property: KProperty<*>,
                    value: Set<String>?
                ) {
                    thisRef.preferences.edit().putStringSet(property.name, value).apply()
                }
            }
    }
}
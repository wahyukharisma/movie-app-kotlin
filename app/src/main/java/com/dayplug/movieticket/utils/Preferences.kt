package com.dayplug.movieticket.utils

import android.content.Context
import android.content.SharedPreferences

class Preferences (val context: Context) {
    companion object{
        const val USER_PREF = "USER_PREF"
    }

    private var _sharedPreferences = context.getSharedPreferences(USER_PREF, 0)

    fun setValues(key: String, value: String){
        val editor: SharedPreferences.Editor = _sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getValue(key: String) : String? {
        return _sharedPreferences.getString(key,"")
    }
}
package com.example.github.data.local

import android.content.Context
import com.example.chatappwithfirebase.utils.BooleanPreference
import com.example.chatappwithfirebase.utils.StringPreference
import com.example.github.app.App

class LocalStorage {

    companion object {
        val pref = App.instance.getSharedPreferences("ChatAppSharedPref", Context.MODE_PRIVATE)
    }

    var token by StringPreference(pref)
    var code by StringPreference(pref)
    var isReg by BooleanPreference(pref)
}
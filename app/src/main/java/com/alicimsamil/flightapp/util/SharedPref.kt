package com.alicimsamil.flightapp.util

import android.content.Context
import android.content.SharedPreferences


class SharedPref(private val context: Context) {

    fun provideSharedPref(): SharedPreferences =
        context.getSharedPreferences(Constants.SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)


    fun provideSharedPrefEditor(): SharedPreferences.Editor =
        provideSharedPref().edit()

    fun saveUser(email: String, password: String) {
        with(provideSharedPrefEditor()) {
            putString(Constants.EMAIL, email)
            putString(Constants.PASSWORD, password)
            apply()
        }
    }

    fun getUserEmail() : String?{
        return provideSharedPref().getString(Constants.EMAIL,null)
    }

    fun getUserPassword() : String?{
        return provideSharedPref().getString(Constants.PASSWORD,null)
    }

    fun setRememberState(checkState:Boolean){
        with(provideSharedPrefEditor()){
            putBoolean(Constants.REMEMBER_STATE,checkState)
            apply()
        }
    }

    fun checkRememberState() : Boolean {
        return provideSharedPref().getBoolean(Constants.REMEMBER_STATE,false)
    }

}
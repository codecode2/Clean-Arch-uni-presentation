package com.learningwithmanos.uniexercise

import android.app.Application
import com.learningwithmanos.uniexercise.heroes.utils.sharedpreferences.MyPreferences
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MyApplication : Application()
{

    companion object {
        lateinit var preferences: MyPreferences
            private set
    }

    override fun onCreate() {
        super.onCreate()
        preferences = MyPreferences(applicationContext)
    }



}

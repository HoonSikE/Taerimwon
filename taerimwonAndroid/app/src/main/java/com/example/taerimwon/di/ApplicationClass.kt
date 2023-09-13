package com.example.taerimwon.di

import android.app.Application
import com.example.taerimwon.utils.preference.PreferenceUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class ApplicationClass: Application() {
    companion object {
        lateinit var userId: String
        private lateinit var application: ApplicationClass
        fun getInstance() : ApplicationClass = application
        lateinit var prefs: PreferenceUtil
    }

    override fun onCreate(){
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
        application = this
    }

}
package com.example.coldstarttestapp.data.app

import android.app.Application

class MyApp : Application() {

    override fun onCreate() {
        super.onCreate()

        fromApplication = true
    }

    companion object {
        var fromApplication: Boolean = false
    }
}
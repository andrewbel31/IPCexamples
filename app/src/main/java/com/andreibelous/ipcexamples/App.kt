package com.andreibelous.ipcexamples

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        ProcessHelper.logProcessName(this)
    }
}
package com.andreibelous.ipcexamples

import android.app.Service
import android.content.Intent
import android.os.IBinder

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        ProcessHelper.logProcessName(this)
        return super.onStartCommand(intent, flags, startId)
    }
}
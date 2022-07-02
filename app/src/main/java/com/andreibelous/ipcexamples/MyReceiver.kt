package com.andreibelous.ipcexamples

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class MyReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        ProcessHelper.logProcessName(this)
    }
}
package com.andreibelous.ipcexamples

import android.app.Service
import android.content.Intent
import android.content.res.AssetFileDescriptor
import android.os.IBinder

class MyService : Service() {

    override fun onBind(intent: Intent): IBinder =

        object : IFileDescriptor.Stub() {
            override fun file(): AssetFileDescriptor =
                resources.openRawResourceFd(R.raw.london)
        }
}
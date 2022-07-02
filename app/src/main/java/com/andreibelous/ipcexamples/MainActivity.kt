package com.andreibelous.ipcexamples

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val processNameTextView = findViewById<TextView>(R.id.process_name)
        processNameTextView.text = ProcessHelper.currentProcessFullName

        ProcessHelper.logProcessName(this)

        sendBroadcast(Intent(this, MyReceiver::class.java))
        startService(Intent(this, MyService::class.java))

        contentResolver.getType(Uri.parse("content://com.andreibelous.ipcexample"))
    }
}
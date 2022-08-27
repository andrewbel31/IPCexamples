package com.andreibelous.ipcexamples

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.content.res.AssetFileDescriptor
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.IBinder
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var image: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // finding image view which we'll use to display our bitmap
        image = findViewById(R.id.image)

        val connection = object : ServiceConnection {

            override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
                // creating link to remote service
                val remote = IFileDescriptor.Stub.asInterface(service)
                // obtaining AssetFileDescriptor
                val fd = remote.file()
                // reading it and displaying to bitmap
                readFile(fd)
            }

            override fun onServiceDisconnected(name: ComponentName?) {
                // clean resources
            }
        }
        bindService(Intent(this, MyService::class.java), connection, Context.BIND_AUTO_CREATE)
    }

    private fun readFile(fd: AssetFileDescriptor) {
        val stream = AssetFileDescriptor.AutoCloseInputStream(fd)
        val bitmap = BitmapFactory.decodeStream(stream)
        image.setImageBitmap(bitmap)
    }

    private fun measure(block: () -> Unit): Long {
        val start = System.currentTimeMillis()
        block()
        val end = System.currentTimeMillis()
        return end - start
    }
}
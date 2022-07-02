package com.andreibelous.ipcexamples

import android.app.Application
import android.os.Build
import android.os.Process
import android.os.StrictMode
import android.util.Log
import java.io.BufferedReader
import java.io.FileReader

object ProcessHelper {

    /**
     * @return full name of current process
     *
     * Full name looks like `your.app.package:yourProcessName`
     */
    @JvmStatic
    val currentProcessFullName: String?
        get() {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                Application.getProcessName()
            } else {
                readProcessName(Process.myPid())
            }
        }

    private fun readProcessName(pid: Int): String? {
        if (pid <= 0) {
            return null
        }

        return runCatching {
            val bufferedReader: BufferedReader
            val oldThreadPolicy = StrictMode.allowThreadDiskReads()

            try {
                bufferedReader = BufferedReader(FileReader("/proc/$pid/cmdline"))
            } finally {
                StrictMode.setThreadPolicy(oldThreadPolicy)
            }

            val processName = bufferedReader.readLine().trim { it <= ' ' }

            runCatching { bufferedReader.close() }

            return@runCatching processName
        }.getOrNull()
    }

    fun logProcessName(parent: Any) {
        Log.d(
            "ProcessHelper1",
            "process name for $parent is $currentProcessFullName"
        )
    }

}
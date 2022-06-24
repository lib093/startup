package org.qcit.startup.task

import android.content.Context
import android.os.SystemClock
import android.util.Log
import org.qcit.lib_startup.interfaces.AndroidStartup
import org.qcit.lib_startup.interfaces.Startup

class Taskfirst: AndroidStartup<Void?>() {
    var name ="Task1"
    override fun create(conexte: Context): Void? {
        Log.d("lib093", " 任务1 正在执行")
        SystemClock.sleep(3_000)
        Log.d("lib093", " 任务1 执行完成")
     return null
    }

    override fun callCreateOnMainThread(): Boolean {
        return false
    }

    override fun waitOnMainThread(): Boolean {
        return false
    }

    override fun dependencies(): List<Class<out Startup<*>>>? {
        return null
    }

    override fun getN(): String {
        return name
    }

}
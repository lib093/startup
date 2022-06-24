package org.qcit.startup.task

import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.SystemClock
import android.util.Log
import org.qcit.lib_startup.interfaces.AndroidStartup
import org.qcit.lib_startup.interfaces.Startup

class Task4 :AndroidStartup<Void?>() {
    var name ="Task4"

    override fun create(conexte: Context): Void? {
        Log.d("lib093", "任务4: 开始执行")
        SystemClock.sleep(5_000)
        Log.d("lib093", " 任务4 执行完成")
        return null
    }

    override fun callCreateOnMainThread(): Boolean {
        return false
    }

    override fun waitOnMainThread(): Boolean {
        return true
    }

    override fun dependencies(): List<Class<out Startup<*>>>? {
        //return arrayListOf(TaskSeencd::class.java)
        return null
    }

    override fun getN(): String {
        return name
    }
}
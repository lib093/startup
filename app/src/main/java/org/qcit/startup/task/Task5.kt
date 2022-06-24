package org.qcit.startup.task

import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.SystemClock
import android.util.Log
import org.qcit.lib_startup.interfaces.AndroidStartup
import org.qcit.lib_startup.interfaces.Startup

class Task5 :AndroidStartup<Void?>() {
    var name ="Task5"
    override fun create(conexte: Context): Void? {
        Log.d("lib093", "任务5: 开始执行")
        SystemClock.sleep(1_000)
        Log.d("lib093", " 任务5 执行完成")
        return null
    }

    override fun callCreateOnMainThread(): Boolean {
        return false
    }

    override fun waitOnMainThread(): Boolean {
        return false
    }

    override fun dependencies(): List<Class<out Startup<*>>>? {
//        return arrayListOf(Task3::class.java,Task4::class.java)
        return null
    }

    override fun getN(): String {
        return name
    }
}
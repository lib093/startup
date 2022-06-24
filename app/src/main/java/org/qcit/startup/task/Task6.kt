package org.qcit.startup.task

import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.SystemClock
import android.util.Log
import org.qcit.lib_startup.interfaces.AndroidStartup
import org.qcit.lib_startup.interfaces.Startup

class Task6 :AndroidStartup<Void?>() {
    var name ="Task6"

    override fun create(conexte: Context): Void? {
        Log.d("lib093", "任务6: 开始执行")
        SystemClock.sleep(6000)
        Log.d("lib093", " 任务6 执行完成")
        return null
    }

    override fun callCreateOnMainThread(): Boolean {
        return true
    }

    override fun waitOnMainThread(): Boolean {
        return false
    }

    override fun dependencies(): List<Class<out Startup<*>>>? {
//        return arrayListOf(Taskfirst::class.java)
        return null
    }

    override fun toString(): String {
        return mWaitCountDown.count.toString()
    }

    override fun getN(): String {
        return name
    }
}
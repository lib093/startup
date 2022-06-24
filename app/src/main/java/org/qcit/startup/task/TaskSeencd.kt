package org.qcit.startup.task

import android.content.Context
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.os.SystemClock
import android.util.Log
import org.qcit.lib_startup.interfaces.AndroidStartup
import org.qcit.lib_startup.interfaces.Startup

class TaskSeencd :AndroidStartup<Void?>() {
    var name ="Task2"
//    var dependencies = mutableListOf<Class<out Startup<*>>>()
//    init {
//        dependencies.add()
//    }
    override fun create(conexte: Context): Void? {
        Log.d("lib093", "任务2: 开始执行")
        SystemClock.sleep(2_000)
        Log.d("lib093", " 任务2 执行完成")
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

    override fun getN(): String {
        return name
    }
}
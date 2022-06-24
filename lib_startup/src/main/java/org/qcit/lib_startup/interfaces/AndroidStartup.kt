package org.qcit.lib_startup.interfaces

import android.os.Process
import android.util.Log
import android.widget.Toast
import org.qcit.lib_startup.manage.ExecutorManager
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executor
import kotlin.math.log

abstract class AndroidStartup<T> : Startup<T> {
     var mWaitCountDown = CountDownLatch(getDependenciesCount())
      fun getDependenciesCount(): Int {

         var dependencies = dependencies()
         var num = when(dependencies){
             null -> 0
             else -> dependencies.size
         }
         return num
     }

    override fun executor(): Executor {
        return ExecutorManager.instance.ioExecutor
    }

    override fun toWait() {
        try {
            mWaitCountDown.await()
        }catch (e:InterruptedException){
            e.printStackTrace()
        }
    }

    override fun toNotify() {
        mWaitCountDown.countDown()
    }

    override fun getThreadPriority(): Int {
        return Process.THREAD_PRIORITY_DEFAULT
    }
}
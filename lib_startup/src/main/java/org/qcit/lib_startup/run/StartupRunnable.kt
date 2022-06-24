package org.qcit.lib_startup.run

import android.content.Context
import android.os.Process
import android.util.Log
import org.qcit.lib_startup.entity.Result
import org.qcit.lib_startup.interfaces.Startup
import org.qcit.lib_startup.manage.StartupCacheManager
import org.qcit.lib_startup.manage.StartupManager

class StartupRunnable(var context: Context,var startup: Startup<*>,var startupManager: StartupManager):Runnable {

    override fun run() {
        Process.setThreadPriority(startup.getThreadPriority())
        startup.toWait()
        var result = startup.create(context)
        StartupCacheManager.getInstance().saveInitializedComponent(startup.javaClass, Result(result))
        startupManager.notifyChildren(startup)
    }
}
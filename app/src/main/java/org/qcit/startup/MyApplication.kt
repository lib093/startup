package org.qcit.startup

import android.app.Application
import org.qcit.lib_startup.manage.StartupManager
import org.qcit.startup.task.*

class MyApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        StartupManager.Companion.Builder()
            .addStartup(Task5())
            .addStartup(TaskSeencd())
            .addStartup(Task6())
            .addStartup(Task3())

            .addStartup(Taskfirst())
            .addStartup(Task4())
            .buid(this)
            .start()
    }
}
package org.qcit.lib_startup.manage

import org.qcit.lib_startup.entity.Result
import org.qcit.lib_startup.interfaces.Startup
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.ConcurrentMap

class StartupCacheManager private constructor(){
    private var mInitializedComponents = ConcurrentHashMap<Class<out Startup<*>>,Result<*>>()
    companion object{
        @Volatile private var mInstance:StartupCacheManager? = null
        fun getInstance() = mInstance ?: synchronized(StartupCacheManager::class.java){
            mInstance?:StartupCacheManager().also { mInstance = it }
        }
    }

    fun saveInitializedComponent(clz:Class<out Startup<*>>,result:Result<*>){
        mInitializedComponents[clz] = result
    }
    fun hadInitialized(clz: Class<out Startup<*>>):Boolean{
        return mInitializedComponents.contains(clz)
    }
}
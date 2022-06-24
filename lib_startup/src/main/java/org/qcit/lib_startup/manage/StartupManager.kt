package org.qcit.lib_startup.manage

import android.content.Context
import android.os.Looper
import android.util.Log
import org.qcit.lib_startup.entity.Result
import org.qcit.lib_startup.entity.StartupSortStore
import org.qcit.lib_startup.interfaces.AndroidStartup
import org.qcit.lib_startup.interfaces.Startup
import org.qcit.lib_startup.run.StartupRunnable
import org.qcit.lib_startup.sort.TopologySort
import java.lang.RuntimeException

class StartupManager constructor(var context: Context,var startupList:List<AndroidStartup<*>>) {

    private lateinit var startupSortStore:StartupSortStore
    fun start():StartupManager{
        if (Looper.myLooper() != Looper.getMainLooper()){
            throw RuntimeException("请在主线程中调用")
        }
        if (startupList == null || startupList.isEmpty()){
            throw RuntimeException("任务不能为空")
        }
        startupSortStore = TopologySort.sort(startupList)
        startupSortStore.result.forEach(){
            var startupRunnable = StartupRunnable(context, it,this)
           if (it.callCreateOnMainThread())
               startupRunnable.run()
            else
                it.executor().execute(startupRunnable)
//            var result = it.create(context)//任务执行
//            StartupCacheManager.getInstance().saveInitializedComponent(it.javaClass,Result(result))
        }
        return this
    }

    fun notifyChildren(startup: Startup<*>){
        //获得已经完成的当前任务的所有子任务
        if (startupSortStore.startupChildrenMap.containsKey(startup.javaClass)){
            var childStartupCls = startupSortStore.startupChildrenMap.get(startup.javaClass)
            childStartupCls?.forEach(){
                //Log.d("lib093", "${ startupSortStore.startupMap.get(it)!!.getN()}")
                startupSortStore.startupMap.get(it)!!.toNotify()
            }
        }
    }
    companion object{
        class Builder{
            private var startupList = mutableListOf<AndroidStartup<*>>()
            fun addStartup(startup: AndroidStartup<*>):Builder{
                startupList.add(startup)
                return this
            }
            fun addAllStartup(startups: List<AndroidStartup<*>>):Builder{
                startupList.addAll(startups)
                return this
            }
            fun buid(context: Context):StartupManager{
                return StartupManager(context,startupList)
            }
        }
    }

}
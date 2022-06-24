package org.qcit.lib_startup.sort

import android.util.Log
import org.qcit.lib_startup.entity.StartupSortStore
import org.qcit.lib_startup.interfaces.Startup
import java.util.*
import kotlin.collections.ArrayList
import kotlin.math.log

/**
 * 拓扑排序
 */
class TopologySort {
    companion object{
        fun sort(startupList:List<out Startup<*>>): StartupSortStore {
            var inDegreeMap = mutableMapOf<Class<out Startup<*>>,Int>()//入度表
            val zeroDeque: Deque<Class<out Startup<*>?>> = ArrayDeque()//0入度表
            var startupMap = mutableMapOf<Class<out Startup<*>>,Startup<*>>()
            var startupChildrenMap = mutableMapOf<Class<out Startup<*>>,MutableList<Class<out Startup<*>>>>()//任务依赖表

            // 找出图中0入度的点
            startupList.forEach(){
                startupMap.put(it.javaClass,it)
                //记录每个任务的入度数
                var dependenciesCount = it.dependencies()?.size?:0
                inDegreeMap.put(it.javaClass,dependenciesCount)
                //记录入度数为0的任务
                if (dependenciesCount == 0){
                    zeroDeque.offer(it.javaClass)
                }else{
                    //遍历本任务所依赖的任务
                    var list = it.dependencies()
                    for ( parent in list!!){
                        var children:MutableList<Class<out Startup<*>>>? = startupChildrenMap.get(parent)
                        if (children == null){
                            children = mutableListOf()
                            //记录这个父任务的所有子任务
                            children.add(it.javaClass)
                            startupChildrenMap.put(parent,children)
                        }else{
                            children.add(it.javaClass)
                            startupChildrenMap.put(parent,children)
                        }
                    }
//                    it.dependencies()?.forEach { parent ->
//                        var children:List<Class<out Startup<*>>>? = startupChildrenMap.get(parent)
//                        if (children == null){
//                            children = ArrayList()
//                            //记录这个父任务的所有子任务
//                            startupChildrenMap.put(parent,children)
//                        }
//                        children =  children.plus()
//
//                    }
                }
            }
            //依次在图中删除这些点
            var result = mutableListOf<Startup<*>>()
            //添加两个集合用来分离子线程和主线程执行的任务
            var main = mutableListOf<Startup<*>>()
            var threads = mutableListOf<Startup<*>>()
            //处理入度为0的任务
            while (!zeroDeque.isEmpty()){
                var cls = zeroDeque.poll()
                var startup = startupMap.get(cls)!!
//                result.add(startup!!)
                if (startup.callCreateOnMainThread()){
                    main.add(startup)
                }else{
                    threads.add(startup)
                }
                //删除后再寻找0入度的点
                if (startupChildrenMap.containsKey(cls)){
                    var childStartup = startupChildrenMap.get(cls)
                    childStartup?.forEach(){
                        var num = inDegreeMap.get(it) as Int
                        inDegreeMap.put(it,num-1)
                        if (num -1 == 0){
                            zeroDeque.offer(it)
                        }
                    }
                }
            }
            result.addAll(threads)
            result.addAll(main)//将子线程任务排在前面 就可以让同等级的子线程任务和主线程任务并行执行了
          return StartupSortStore(result,startupMap,startupChildrenMap)
        }
    }
}
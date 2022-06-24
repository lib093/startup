package org.qcit.lib_startup.interfaces

import android.content.Context

interface Startup<T>:Dispatcher {
    fun create(conexte:Context):T

    /**
     * 本任务依赖的任务
     */
    fun dependencies(): List<Class<out Startup<*>>>?
  //本任务的入度数
    fun getN():String
}
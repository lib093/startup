package org.qcit.lib_startup.interfaces

import java.util.concurrent.Executor

interface Dispatcher {
    /**
     * 是否在主线程中运行
     */
    fun callCreateOnMainThread():Boolean;

    /**
     * 是否需要等待该任务执行完成
     * 该任务在子线程中执行的任务 主线程是否需要等待他
     */
    fun waitOnMainThread():Boolean

    /**
     * 等待
     */
    fun toWait()

    /**
     * 有父任务执行完毕时
     * 计数器-1
     */
    fun toNotify()

    fun executor():Executor

    fun getThreadPriority():Int
}
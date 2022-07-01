# startup

#### 介绍
任务执行框架 实现了多任务协作执行，子线程执行

任务排序采用拓扑排序
线程排序采用CountDownLatch


#### 使用说明
        StartupManager.Companion.Builder()
            .addStartup(Task5())
            .addStartup(TaskSeencd())
            .addStartup(Task6())
            .addStartup(Task3())
            .addStartup(Taskfirst())
            .addStartup(Task4())
            .buid(this)
            .start()
#### 运行结果
![输入图片说明](https://images.gitee.com/uploads/images/2022/0701/084328_4c43289c_632426.png "屏幕截图.png")

任务
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


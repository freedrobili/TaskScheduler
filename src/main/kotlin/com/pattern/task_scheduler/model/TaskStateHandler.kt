package com.pattern.task_scheduler.model

interface TaskStateHandler {
    fun start(task: Task)
    fun complete(task: Task)
    fun revert(task: Task)
}

package com.pattern.task_scheduler.model

import com.pattern.task_scheduler.model.Task


interface TaskStateHandler {
    fun start(task: Task)
    fun complete(task: Task)
    fun revert(task: Task)
}

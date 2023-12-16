package com.pattern.task_scheduler.model

import com.pattern.task_scheduler.TaskState


object PlannedStateHandler : TaskStateHandler {
    override fun start(task: Task) {
        task.state = TaskState.IN_PROGRESS
    }

    override fun complete(task: Task) {
        // Planned tasks cannot be completed directly
    }

    override fun revert(task: Task) {
        // No action needed when reverting from Planned state
    }
}

object InProgressStateHandler : TaskStateHandler {
    override fun start(task: Task) {
        // Task is already in progress
    }

    override fun complete(task: Task) {
        task.state = TaskState.COMPLETED
    }

    override fun revert(task: Task) {
        task.state = TaskState.PLANNED
    }
}

object CompletedStateHandler : TaskStateHandler {
    override fun start(task: Task) {
        // Completed tasks cannot be started again
    }

    override fun complete(task: Task) {
        // Task is already completed
    }

    override fun revert(task: Task) {
        task.state = TaskState.IN_PROGRESS
    }
}

package com.pattern.task_scheduler.model

import com.pattern.task_scheduler.TaskState
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id


@Entity
data class Task(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var title: String = "",
    var description: String? = null,
    var assignedTo: String? = null,
    var createdBy: String = "",
    var isFavorite: Boolean = false,
    @Enumerated(EnumType.STRING)
    var state: TaskState = TaskState.PLANNED
) : TaskStateHandler {
    override fun start(task: Task) {
        stateHandler.start(task)
    }

    override fun complete(task: Task) {
        stateHandler.complete(task)
    }

    override fun revert(task: Task) {
        stateHandler.revert(task)
    }

    private val stateHandler: TaskStateHandler
        get() {
            return when (state) {
                TaskState.PLANNED -> PlannedStateHandler
                TaskState.IN_PROGRESS -> InProgressStateHandler
                TaskState.COMPLETED -> CompletedStateHandler
            }
        }
}

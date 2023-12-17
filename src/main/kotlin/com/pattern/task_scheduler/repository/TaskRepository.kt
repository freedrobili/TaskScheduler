package com.pattern.task_scheduler.repository

import com.pattern.task_scheduler.TaskState
import com.pattern.task_scheduler.model.Task
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository : JpaRepository<Task, Long> {
//    fun findByCreatedBy(username: String): List<Task>
//    fun findByAssignedTo(username: String): List<Task>
//    fun findByCreatedByAndIsFavorite(username: String, isFavorite: Boolean): List<Task>
//    fun findByUserUsernameAndState(username: String, state: TaskState): List<Task>
fun findByCreatedByAndState(username: String, state: TaskState): List<Task>
    fun findByAssignedToAndState(username: String, state: TaskState): List<Task>
    fun findByCreatedByAndIsFavoriteAndState(username: String, isFavorite: Boolean, state: TaskState): List<Task>
}
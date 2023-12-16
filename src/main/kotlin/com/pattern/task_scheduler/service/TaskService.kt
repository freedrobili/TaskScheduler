package com.pattern.task_scheduler.service

import com.pattern.task_scheduler.TaskState
import com.pattern.task_scheduler.model.Task
import com.pattern.task_scheduler.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService(private val taskRepository: TaskRepository) {
    fun getTasksCreatedBy(username: String): List<Task> {
        return taskRepository.findByCreatedBy(username)
    }

    fun getTasksAssignedTo(username: String): List<Task> {
        return taskRepository.findByAssignedTo(username)
    }

    fun getFavoriteTasks(username: String): List<Task> {
        return taskRepository.findByCreatedByAndIsFavorite(username, true)
    }

    fun addTask(task: Task): Task {
        return taskRepository.save(task)
    }

//    fun startTask(taskId: Long) {
//        val task = taskRepository.findById(taskId)
//        task.ifPresent {
//            it.start(it)
//            taskRepository.save(it)
//        }
//    }
fun startTask(taskId: Long) {
    val task = taskRepository.findById(taskId)
    task.ifPresent {
        it.start(it)
        taskRepository.save(it)
    }
}

    fun getTasksInProgress(username: String): List<Task> {
        return taskRepository.findByUserUsernameAndState(username, TaskState.IN_PROGRESS)
    }

    fun completeTask(taskId: Long) {
        val task = taskRepository.findById(taskId)
        task.ifPresent {
            it.complete(it)
            taskRepository.save(it)
        }
    }

    fun revertTask(taskId: Long) {
        val task = taskRepository.findById(taskId)
        task.ifPresent {
            it.revert(it)
            taskRepository.save(it)
        }
    }
    // Другие методы, например, удаление задачи и другие операции.
}
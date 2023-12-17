package com.pattern.task_scheduler.service

import com.pattern.task_scheduler.TaskState
import com.pattern.task_scheduler.model.Task
import com.pattern.task_scheduler.repository.TaskRepository
import org.springframework.stereotype.Service

@Service
class TaskService(private val taskRepository: TaskRepository) {
    fun getTasksCreatedBy(username: String): List<Task> {
        return taskRepository.findByCreatedByAndState(username, TaskState.PLANNED)
    }

    fun getTasksAssignedTo(username: String): List<Task> {
        return taskRepository.findByAssignedToAndState(username, TaskState.IN_PROGRESS)
    }

    fun getFavoriteTasks(username: String): List<Task> {
        return taskRepository.findByCreatedByAndIsFavoriteAndState(username, true, TaskState.PLANNED)
    }

    fun addTask(task: Task): Task {
        return taskRepository.save(task)
    }
/*    fun startTask(taskId: Long) {
        val task = taskRepository.findById(taskId)
        task.ifPresent {
            it.stateHandler.start(it)
            taskRepository.save(it)
        }
    }*/


    fun startTask(taskId: Long) {
        val task = taskRepository.findById(taskId)
        task.ifPresent {
            it.stateHandler.start(it)
            if (it.state == TaskState.IN_PROGRESS) {
                // Переносим задачу в раздел "Tasks In Progress"
                it.assignedTo = "currentLoggedInUser" // Замените на актуальное имя пользователя
                taskRepository.save(it)
            }
        }
    }

    fun completeTask(taskId: Long) {
        val task = taskRepository.findById(taskId)
        task.ifPresent {
            it.stateHandler.complete(it)
            if (it.state == TaskState.COMPLETED) {
                // Переносим завершенную задачу в раздел Завершенные
                it.assignedTo = null  // или установите другого пользователя, если нужно
                taskRepository.save(it)
            }
        }
    }

/*    fun startTask(taskId: Long) {
        val task = taskRepository.findById(taskId)
        task.ifPresent {
            it.assignedTo = null  // или установите другого пользователя, если нужно
            it.state = TaskState.COMPLETED
            taskRepository.save(it)
        }
    }*/
//    fun startTask(taskId: Long) {
//        val task = taskRepository.findById(taskId)
//        task.ifPresent {
//            it.state = TaskState.IN_PROGRESS
//            taskRepository.save(it)
//        }
//    }
//    fun startTask(taskId: Long) {
//        val task = taskRepository.findById(taskId)
//        task.ifPresent {
//            it.stateHandler.start(it)
//            taskRepository.save(it)
//        }
//    }


/*    fun completeTask(taskId: Long) {
        val task = taskRepository.findById(taskId)
        task.ifPresent {
            it.complete(it)
            if (it.state == TaskState.COMPLETED) {
                // Переносим завершенную задачу в раздел Завершенные
                it.assignedTo = null  // или установите другого пользователя, если нужно
                taskRepository.save(it)
            }
        }
    }*/

/*    fun startTask(taskId: Long) {
        val task = taskRepository.findById(taskId)
        task.ifPresent {
            it.start(it)
            taskRepository.save(it)
        }
    }

    fun completeTask(taskId: Long) {
        val task = taskRepository.findById(taskId)
        task.ifPresent {
            it.complete(it)
            taskRepository.save(it)
        }
    }*/

/*    fun revertTask(taskId: Long) {
        val task = taskRepository.findById(taskId)
        task.ifPresent {
            it.revert(it)
            taskRepository.save(it)
        }
    }*/

    fun revertTask(taskId: Long) {
        val task = taskRepository.findById(taskId)
        task.ifPresent {
            it.stateHandler.revert(it)
            if (it.state == TaskState.IN_PROGRESS) {
                // Переносим задачу в раздел "Tasks In Progress"
                it.assignedTo = "currentLoggedInUser" // Замените на актуальное имя пользователя
                taskRepository.save(it)
            }
        }
    }

    fun getTasksInProgress(username: String): List<Task> {
        return taskRepository.findByAssignedToAndState(username, TaskState.IN_PROGRESS)
    }

    fun getCompletedTasks(username: String): List<Task> {
        return taskRepository.findByCreatedByAndState(username, TaskState.COMPLETED)
    }

/*    fun addTask(task: Task): Task {
        return taskRepository.save(task)
    }

    fun getTasksInProgress(username: String): List<Task> {
        return taskRepository.findByUserUsernameAndState(username, TaskState.IN_PROGRESS)
    }

    fun getCompletedTasks(username: String): List<Task> {
        return taskRepository.findByUserUsernameAndState(username, TaskState.COMPLETED)
    }*/

    // ... остальные методы

/*    fun startTask(taskId: Long) {
        val task = taskRepository.findById(taskId)
        task.ifPresent {
            it.start(it)
            taskRepository.save(it)
        }
    }

    fun completeTask(taskId: Long) {
        val task = taskRepository.findById(taskId)
        task.ifPresent {
            it.complete(it)
            taskRepository.save(it)
        }
    }*/
   /* fun startTask(taskId: Long) {
        val task = taskRepository.findById(taskId)
        task.ifPresent {
            it.start(it)
            taskRepository.save(it)
        }
    }*/

/*    fun completeTask(taskId: Long) {
        val task = taskRepository.findById(taskId)
        task.ifPresent {
            it.complete(it)
            taskRepository.save(it)
        }
    }*/

/*    fun revertTask(taskId: Long) {
        val task = taskRepository.findById(taskId)
        task.ifPresent {
            it.revert(it)
            taskRepository.save(it)
        }
    }*/
    // Другие методы, например, удаление задачи и другие операции.
}
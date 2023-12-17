package com.pattern.task_scheduler.controller

import com.pattern.task_scheduler.model.Task
import com.pattern.task_scheduler.service.TaskService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping

@Controller
@RequestMapping("/tasks")
class TaskController(private val taskService: TaskService) {

    @GetMapping("/createdByMe")
    fun tasksCreatedByMe(model: Model): String {
        val username = "currentLoggedInUser"
        val tasks = taskService.getTasksCreatedBy(username)
        model.addAttribute("tasks", tasks)
        return "tasks/createdByMe"
    }

    @GetMapping("/favorites")
    fun favoriteTasks(model: Model): String {
        val username = "currentLoggedInUser"
        val tasks = taskService.getFavoriteTasks(username)
        model.addAttribute("tasks", tasks)
        return "tasks/favorites"
    }

    @GetMapping("/add")
    fun showAddTaskForm(model: Model): String {
        model.addAttribute("task", Task())
        return "tasks/add"
    }

    @PostMapping("/add")
    fun addTask(task: Task): String {
        val username = "currentLoggedInUser"
        task.createdBy = username
        taskService.addTask(task)
        return "redirect:/tasks/createdByMe"
    }

    @GetMapping("/start/{taskId}")
    fun startTask(@PathVariable taskId: Long): String {
        taskService.startTask(taskId)
        return "redirect:/tasks/createdByMe"
    }

    @GetMapping("/complete/{taskId}")
    fun completeTask(@PathVariable taskId: Long): String {
        taskService.completeTask(taskId)
        return "redirect:/tasks/createdByMe"
    }

    @GetMapping("/revert/{taskId}")
    fun revertTask(@PathVariable taskId: Long): String {
        taskService.revertTask(taskId)
        return "redirect:/tasks/createdByMe"
    }

    @GetMapping("/inProgress")
    fun tasksInProgress(model: Model): String {
        val username = "currentLoggedInUser"
        val tasks = taskService.getTasksInProgress(username)
        model.addAttribute("tasks", tasks)
        return "tasks/inProgress"
    }

    @GetMapping("/completed")
    fun completedTasks(model: Model): String {
        val username = "currentLoggedInUser"
        val tasks = taskService.getCompletedTasks(username)
        model.addAttribute("tasks", tasks)
        return "tasks/completed"
    }
}
package com.example.mvvmproject_android.model

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TaskRepository private constructor() {
    private val taskList = mutableListOf<Task>()
    private var nextId = 1

    private fun getCurrentDate(): String {
        val sdf = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
        return sdf.format(Date())
    }

    fun getTasks(): List<Task> {
        return taskList.toList()
    }

    fun addTask(title: String) {
        taskList.add(Task(id = nextId++, title = title, date = getCurrentDate()))
    }

    fun updateTask(id: Int, newTitle: String) {
        val index = taskList.indexOfFirst { it.id == id }
        if (index != -1) {
            val task = taskList[index]
            taskList[index] = task.copy(title = newTitle, date = getCurrentDate())
        }
    }

    fun deleteTask(task: Task) {
        taskList.remove(task)
    }

    fun toggleTask(task: Task) {
        val index = taskList.indexOfFirst { it.id == task.id }
        if (index != -1) {
            taskList[index].isCompleted = !taskList[index].isCompleted
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: TaskRepository? = null

        fun getInstance(): TaskRepository {
            return INSTANCE ?: synchronized(this) {
                val instance = TaskRepository()
                INSTANCE = instance
                instance
            }
        }
    }
}
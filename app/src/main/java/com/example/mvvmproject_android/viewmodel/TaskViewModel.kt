package com.example.mvvmproject_android.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmproject_android.model.Task
import com.example.mvvmproject_android.model.TaskRepository

class TaskViewModel : ViewModel() {
    private val repository = TaskRepository.getInstance()
    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>> = _tasks
    init { loadTasks() }
    fun loadTasks() {
        _tasks.value = repository.getTasks()
    }
    fun addTask(title: String) {
        repository.addTask(title)
        loadTasks()
    }
    fun updateTask(id: Int, newTitle: String) {
        repository.updateTask(id, newTitle)
        loadTasks()
    }
    fun deleteTask(task: Task) {
        repository.deleteTask(task)
        loadTasks()
    }
    fun toggleComplete(task: Task) {
        repository.toggleTask(task)
        loadTasks()
    }
}
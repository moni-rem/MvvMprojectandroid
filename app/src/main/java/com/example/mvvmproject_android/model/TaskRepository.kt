package com.example.mvvmproject_android.model

 class TaskRepository{
    private val taskList = mutableListOf<Task>()
    private var nextId = 1


     fun getTasks(): List<Task> {
        return taskList.toList()
    }

     fun addTask(title: String) {
         taskList.add(Task(id = nextId++, title = title))
     }

     fun deleteTask(task: Task) {
         taskList.remove(task)
     }
     fun toggleTask(task: Task){
         task.isCompleted = !task.isCompleted
     }



}
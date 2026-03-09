package com.example.mvvmproject_android.model

data class Task(
    var id: Int,
    val title: String,
    var isCompleted: Boolean = false
)
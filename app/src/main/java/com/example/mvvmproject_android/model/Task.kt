package com.example.mvvmproject_android.model

data class Task(
    val id: Int,
    val title: String,
    var isCompleted: Boolean = false,
    val date: String
)
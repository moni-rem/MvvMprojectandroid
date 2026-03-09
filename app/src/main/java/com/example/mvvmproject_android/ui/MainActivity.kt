package com.example.mvvmproject_android.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmproject_android.databinding.ActivityMainBinding
import com.example.mvvmproject_android.ui.adapter.TaskAdapter
import com.example.mvvmproject_android.viewmodel.TaskViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: TaskViewModel by viewModels()
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Fixed typo: TaskAdaper -> TaskAdapter
        adapter = TaskAdapter(
            tasks = listOf(),
            onToggle = { task -> viewModel.toggleComplete(task) },
            onDelete = { task -> viewModel.deleteTask(task) }
        )

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

        viewModel.tasks.observe(this) { tasks ->
            adapter.updateTasks(tasks)
        }

        binding.fabAdd.setOnClickListener {
            startActivity(Intent(this, AddTaskActivity::class.java))
        }
    }
}
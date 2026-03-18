package com.example.mvvmproject_android.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvmproject_android.databinding.ActivityMainBinding
import com.example.mvvmproject_android.ui.adapter.TaskAdapter
import com.example.mvvmproject_android.viewmodel.TaskViewModel

class TaskListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: TaskViewModel by viewModels()
    private lateinit var adapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBackMain.setOnClickListener {
            finish()
        }

        adapter = TaskAdapter(
            tasks = listOf(),
            onToggle = { task -> viewModel.toggleComplete(task) },
            onDelete = { task -> viewModel.deleteTask(task) },
            onEdit = { task ->
                val intent = Intent(this, AddTaskActivity::class.java).apply {
                    putExtra("TASK_ID", task.id)
                    putExtra("TASK_TITLE", task.title)
                }
                startActivity(intent)
            },
            onBack = {
                finish()
            }
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

    override fun onResume() {
        super.onResume()
        viewModel.loadTasks()
    }
}
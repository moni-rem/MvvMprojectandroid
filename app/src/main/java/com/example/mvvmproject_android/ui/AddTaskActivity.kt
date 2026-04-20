package com.example.mvvmproject_android.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmproject_android.databinding.ActivityAddTaskBinding
import com.example.mvvmproject_android.viewmodel.TaskViewModel

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private val viewModel: TaskViewModel by viewModels()
    private var taskId: Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        taskId = intent.getIntExtra("TASK_ID", -1)
        val taskTitle = intent.getStringExtra("TASK_TITLE")
        if (taskId != -1) {
            binding.etTitle.setText(taskTitle)
            binding.tvHeader.text = "Update Task"
            binding.btnSave.text = "Update"
        }

        binding.btnBack.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
            finish()
        }
        binding.btnCancel.setOnClickListener {
            finish()
        }
        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            if (title.isNotBlank()) {
                if (taskId == -1) {
                    viewModel.addTask(title)
                } else {
                    viewModel.updateTask(taskId, title)
                }
                finish()
            }
        }
    }
}
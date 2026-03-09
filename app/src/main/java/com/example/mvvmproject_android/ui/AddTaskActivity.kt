package com.example.mvvmproject_android.ui



import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmproject_android.databinding.ActivityAddTaskBinding
import com.example.mvvmproject_android.viewmodel.TaskViewModel

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private val viewModel: TaskViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSave.setOnClickListener {
            val title = binding.etTitle.text.toString()
            if (title.isNotBlank()) {
                viewModel.addTask(title)
                finish()
            }
        }
    }
}
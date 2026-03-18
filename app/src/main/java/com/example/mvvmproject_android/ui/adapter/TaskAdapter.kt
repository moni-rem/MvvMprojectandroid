package com.example.mvvmproject_android.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvmproject_android.databinding.ItemTaskBinding
import com.example.mvvmproject_android.model.Task
import android.graphics.Paint

class TaskAdapter(
    private var tasks: List<Task>,
    private val onToggle: (Task) -> Unit,
    private val onDelete: (Task) -> Unit,
    private val onEdit: (Task) -> Unit,
    private val onBack: () -> Unit
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.binding.tvTitle.text = task.title
        holder.binding.tvDate.text = task.date
        holder.binding.checkbox.isChecked = task.isCompleted

        if (task.isCompleted) {
            holder.binding.tvTitle.paintFlags = holder.binding.tvTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
            holder.binding.root.alpha = 0.6f
        } else {
            holder.binding.tvTitle.paintFlags = holder.binding.tvTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            holder.binding.root.alpha = 1.0f
        }

        holder.binding.checkbox.setOnClickListener {
            onToggle(task)
        }
        holder.binding.btnDelete.setOnClickListener {
            onDelete(task)
        }
        holder.binding.btnEdit.setOnClickListener {
            onEdit(task)
        }
        holder.binding.btnBackItem.setOnClickListener {
            onBack()
        }
    }

    override fun getItemCount() = tasks.size

    fun updateTasks(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }
}
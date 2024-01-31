package com.nikhil.todoapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.nikhil.todoapp.databinding.TaskItemBinding
import com.nikhil.todoapp.model.Task
import java.util.ArrayList

class TaskAdapter(
    private val context: Context,
    private val taskClickListener: TaskClickListener,
    private val taskStatusClickListener: TaskStatusClickListener,
    private val taskLongClickDeleteListener: TaskLongClickDeleteListener
): RecyclerView.Adapter<TaskAdapter.TaskAdapterViewHolder>() {
    private val allTasks = ArrayList<Task>()
    inner class TaskAdapterViewHolder(val binding: TaskItemBinding): ViewHolder(binding.root) {
        fun bind(task: Task) {
            binding.apply {
                taskDesc.text = task.task
                status.isChecked = task.status
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskAdapterViewHolder {
        return TaskAdapterViewHolder(
            TaskItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return allTasks.size
    }

    override fun onBindViewHolder(holder: TaskAdapterViewHolder, position: Int) {
        val task = allTasks[position]
        holder.bind(task)

        holder.itemView.setOnClickListener {
            taskClickListener.onTaskClick(allTasks[position])
        }

        holder.binding.status.setOnClickListener {
            taskStatusClickListener.onTaskStatusClick(allTasks[position])
        }

        holder.itemView.setOnLongClickListener {
            taskLongClickDeleteListener.onTaskLongClickDeleteListener(allTasks[position])
            true
        }
    }

    fun updateList(newList: List<Task>) {
        // on below line we are clearing
        // our notes array list
        allTasks.clear()
        // on below line we are adding a
        // new list to our all notes list.
        allTasks.addAll(newList)
        // on below line we are calling notify data
        // change method to notify our adapter.
        notifyDataSetChanged()
    }
}

interface TaskClickListener {
    fun onTaskClick(task: Task)
}

interface TaskStatusClickListener {
    fun onTaskStatusClick(task: Task)
}

interface TaskLongClickDeleteListener {
    fun onTaskLongClickDeleteListener(task: Task)
}
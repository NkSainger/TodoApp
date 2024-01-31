package com.nikhil.todoapp.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikhil.todoapp.adapters.TaskAdapter
import com.nikhil.todoapp.adapters.TaskClickListener
import com.nikhil.todoapp.adapters.TaskStatusClickListener
import com.nikhil.todoapp.databinding.ActivityMainBinding
import com.nikhil.todoapp.model.Task
import com.nikhil.todoapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity(), TaskClickListener, TaskStatusClickListener {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModal: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val taskAdapter = TaskAdapter(this, this, this)
        binding.recyclerView.adapter = taskAdapter

        viewModal = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[MainViewModel::class.java]

        viewModal.allNotes.observe(this) { list ->
            list?.let {
                taskAdapter.updateList(it)
            }
        }

        binding.addTask.setOnClickListener {
            val intent = Intent(this@MainActivity, AddUpdateTaskActivity::class.java)
            intent.putExtra("taskType", "Add")
            startActivity(intent)
        }
    }

    override fun onTaskClick(task: Task) {
        val intent = Intent(this@MainActivity, AddUpdateTaskActivity::class.java)
        intent.putExtra("taskType", "Edit")
        intent.putExtra("task", task.task)
        intent.putExtra("id", task.id)
        intent.putExtra("status", task.status)
        startActivity(intent)
        this.finish()
    }

    override fun onTaskStatusClick(task: Task) {
        task.status = !task.status
        viewModal.updateTask(task)
    }
}
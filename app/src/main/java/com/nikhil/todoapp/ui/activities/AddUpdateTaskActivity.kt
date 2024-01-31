package com.nikhil.todoapp.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.nikhil.todoapp.databinding.ActivityAddUpdateTaskBinding
import com.nikhil.todoapp.model.Task
import com.nikhil.todoapp.viewmodel.MainViewModel

class AddUpdateTaskActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddUpdateTaskBinding
    private lateinit var viewModal: MainViewModel
    private var noteID = -1
    private var taskStatus = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUpdateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)

        viewModal = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[MainViewModel::class.java]

        val noteType = intent.getStringExtra("taskType")
        if (noteType.equals("Edit")) {
            // on below line we are setting data to edit text.
            val task = intent.getStringExtra("task")
            taskStatus = intent.getBooleanExtra("status", false)
            noteID = intent.getIntExtra("id", -1)

            binding.apply {
                addTaskBtn.text = "Update Task"
                textView.text = "Update"
                addOrUpdateTaskEdTxt.setText(task)
            }
        } else {
            binding.textView.text = "Add"
            binding.addTaskBtn.text = "Save Task"
        }

        binding.addTaskBtn.setOnClickListener {
            val taskData = binding.addOrUpdateTaskEdTxt.text.toString()

            if(noteType == "Edit") {
                if(taskData.isEmpty()) {
                    binding.addOrUpdateTaskEdTxt.error = "Enter something"
                } else {
                    val updateTask = Task(task = taskData, status = taskStatus)
                    updateTask.id = noteID
                    viewModal.updateTask(updateTask)
                    Toast.makeText(this, "Note Updated..", Toast.LENGTH_LONG).show()
                }
            } else {
                if(taskData.isEmpty()) {
                    binding.addOrUpdateTaskEdTxt.error = "Enter something"
                }else {
                    viewModal.addTask(Task(task = taskData, status = false))
                }
            }

            startActivity(Intent(this@AddUpdateTaskActivity, MainActivity::class.java))
            this.finish()
        }
    }
}
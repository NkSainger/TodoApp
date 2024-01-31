package com.nikhil.todoapp.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nikhil.todoapp.database.TaskDB
import com.nikhil.todoapp.model.Task
import com.nikhil.todoapp.repo.TaskRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(
    application: Application
) : AndroidViewModel(application) {
    val allNotes : LiveData<List<Task>>
    private val repository : TaskRepo

    init {
        val dao = TaskDB.getInstance(application).getTaskDao()
        repository = TaskRepo(dao)
        allNotes = repository.allTasks
    }

    fun deleteTask (task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(task)
    }

    fun updateTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(task)
    }

    fun addTask(task: Task) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(task)
    }
}
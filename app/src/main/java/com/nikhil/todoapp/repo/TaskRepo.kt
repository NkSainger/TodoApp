package com.nikhil.todoapp.repo

import androidx.lifecycle.LiveData
import com.nikhil.todoapp.dao.TaskDao
import com.nikhil.todoapp.model.Task

class TaskRepo(private val taskDao: TaskDao) {
    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    suspend fun insert(task: Task) {
        taskDao.insert(task)
    }

    suspend fun delete(task: Task){
        taskDao.delete(task)
    }

    suspend fun update(task: Task){
        taskDao.update(task)
    }
}
package com.nikhil.todoapp.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nikhil.todoapp.model.Task

@Dao
interface TaskDao {
    @Insert
    fun setTask(task: Task)

    @Query("select * from Task")
    fun getAllTasks(): List<Task>
}
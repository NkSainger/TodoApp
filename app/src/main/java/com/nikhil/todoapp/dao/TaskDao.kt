package com.nikhil.todoapp.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.nikhil.todoapp.model.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task :Task)

    @Delete
    suspend fun delete(task: Task)

    @Query("select * from Task")
    fun getAllTasks(): LiveData<List<Task>>

    @Update
    suspend fun update(task: Task)
}

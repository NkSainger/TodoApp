package com.nikhil.todoapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nikhil.todoapp.dao.TaskDao
import com.nikhil.todoapp.model.Task

@Database(entities = [Task::class], version = 1)
abstract class TaskDB: RoomDatabase() {
    abstract fun getTaskDao(): TaskDao
    companion object {
        fun getInstance(context: Context): TaskDB {
            return Room.databaseBuilder(
                context, TaskDB::class.java, "TaskManagement"
            ).build()
        }
    }
}
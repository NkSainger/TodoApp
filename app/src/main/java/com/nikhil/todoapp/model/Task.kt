package com.nikhil.todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Task(
    @ColumnInfo(name = "task") var task: String,
    @ColumnInfo(name = "task_status") var status: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int ?= null
}
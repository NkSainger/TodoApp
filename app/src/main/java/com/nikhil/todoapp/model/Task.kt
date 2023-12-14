package com.nikhil.todoapp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Task {
    @PrimaryKey(autoGenerate = true)
    var id: Int ?= null

    var task: String ?= null

    @ColumnInfo(name = "task_status")
    var status: Boolean ?= null
}
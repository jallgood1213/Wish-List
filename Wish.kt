package com.example.wish
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish_table")
data class Wish (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val isGranted: Boolean = false
)
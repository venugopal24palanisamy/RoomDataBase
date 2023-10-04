package com.example.roomdatabase.roomDataBase.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user_details")
data class UserDetails(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    val userId: Int? = 0,
    @ColumnInfo
    var name: String?,
    @ColumnInfo
    var bloodGroup: String?
)

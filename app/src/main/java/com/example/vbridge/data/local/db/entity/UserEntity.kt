package com.example.vbridge.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "user_table")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    @NotNull
    val id: Long,

    @ColumnInfo(name = "name")
    @NotNull
    val name: String
) {
}
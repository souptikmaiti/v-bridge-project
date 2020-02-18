package com.example.vbridge.data.local.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.vbridge.data.local.db.entity.UserEntity

@Dao
interface UserDao {
    @Query("SELECT * FROM user_table")
    fun getAll(): List<UserEntity>

    @Insert
    fun insert(entity: UserEntity)

    @Delete
    fun delete(entity: UserEntity)
}
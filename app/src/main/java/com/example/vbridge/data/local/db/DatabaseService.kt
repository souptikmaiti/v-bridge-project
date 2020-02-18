package com.example.vbridge.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.vbridge.data.local.db.dao.UserDao
import com.example.vbridge.data.local.db.entity.UserEntity

@Database( entities = [UserEntity::class],
    version = 1,
    exportSchema = false)
abstract class DatabaseService(): RoomDatabase() {

    abstract fun userDao(): UserDao
}
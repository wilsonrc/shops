package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.model.entity.ShopEntity

@Database(entities = [ShopEntity::class], version = 1, exportSchema = false)
abstract class ShopDatabase : RoomDatabase() {
    abstract fun shopDao(): ShopDao
}
package com.example.inventory.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Item::class],
    version = ItemRoomDatabase.DATABASE_VERSION,
    exportSchema = false
)
abstract class ItemRoomDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {
        internal const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "ItemRoomDB"

        @Volatile
        private var INSTANCE: ItemRoomDatabase? = null

        fun database(context: Context): ItemRoomDatabase =
            INSTANCE ?: synchronized(this) {
                Room
                    .databaseBuilder(context, ItemRoomDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
            }.also { INSTANCE = it }

    }
}
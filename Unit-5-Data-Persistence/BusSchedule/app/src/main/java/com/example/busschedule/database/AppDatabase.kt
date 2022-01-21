package com.example.busschedule.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.busschedule.database.schedule.Schedule
import com.example.busschedule.database.schedule.ScheduleDao

@Database(entities = [Schedule::class], version = AppDatabase.DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase() {

    abstract fun scheduleDao() : ScheduleDao

    companion object {
        private val DATABASE_NAME : String = "BUS_SCHEDULE_DB"
        private val DATABASE_PATH : String = "database/bus_schedule.db"
        internal const val DATABASE_VERSION : Int = 1

        @Volatile
        private var INSTANCE : AppDatabase? = null

        fun database(context : Context) : AppDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context, AppDatabase::class.java, DATABASE_NAME
                ).createFromAsset(DATABASE_PATH).build()
                INSTANCE = instance
                instance
            }
    }
}


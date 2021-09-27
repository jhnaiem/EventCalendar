package com.example.eventcalendar.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.eventcalendar.model.utils.LocalDateConverter
import com.example.eventcalendar.model.utils.LocalDateTimeConverter

@Database(entities = [Event::class], version = 1)
@TypeConverters(
    LocalDateTimeConverter::class,
    LocalDateConverter::class)
abstract class EventDatabase : RoomDatabase() {

    abstract val eventDao: EventDao

    companion object {

        @Volatile
        private var INSTANCE: EventDatabase? = null
        fun getInstance(context: Context): EventDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        EventDatabase::class.java,
                        "event_data_db"
                    ).build()
                }
                return instance
            }
        }


    }

}
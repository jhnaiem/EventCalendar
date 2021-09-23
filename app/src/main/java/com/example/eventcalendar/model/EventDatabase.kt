package com.example.eventcalendar.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Event::class],version = 1)
abstract class EventDatabase:  RoomDatabase(){

    abstract val eventDao: EventDao

    companion object{

        @Volatile
        private var INSTANCE: EventDatabase? = null
        fun getInstance(context: Context): EventDatabase{
            synchronized(this){
                var instance = INSTANCE
                if (instance == null){
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
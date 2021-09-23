package com.example.eventcalendar.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface EventDao {

    @Insert
    suspend fun insertEvent(event: Event):Long

    @Update
    suspend fun updateEvent(event: Event)

    @Delete
    suspend fun deleteEvent(event: Event)

    @Query("SELECT * FROM event_data_table")
    fun getAllEvents(): LiveData<List<Event>>
}
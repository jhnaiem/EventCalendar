package com.example.eventcalendar.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import java.time.LocalDate

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

    @Query("SELECT * FROM event_data_table WHERE event_date IN (:date)")
    suspend fun getEventsByDate(date: LocalDate):List<Event>
}
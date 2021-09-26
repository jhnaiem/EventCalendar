package com.example.eventcalendar.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.eventcalendar.presentation.utils.DateConverter
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(tableName = "event_data_table")
data class Event (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int,

    @ColumnInfo(name = "event_date")
    var eventDate: LocalDate,

    @ColumnInfo(name = "created_date_time")
    var createdDateTime: LocalDateTime,

    @ColumnInfo(name = "updated_date_time")
    var updatedDateTime: LocalDateTime,


    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String
)
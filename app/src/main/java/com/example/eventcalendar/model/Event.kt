package com.example.eventcalendar.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalTime

@Entity(tableName = "event_data_table")
data class Event (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "event_id")
    var id: Int,

    @ColumnInfo(name = "event_date")
    var date: LocalDate,

    @ColumnInfo(name = "event_time")
    var time: LocalTime,

    @ColumnInfo(name = "event_name")
    var title: String,

    @ColumnInfo(name = "event_desc")
    var description: String
)
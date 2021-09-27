package com.example.eventcalendar.model

import java.time.LocalDate


class EventRepo(private val dao: EventDao) {

    val events = dao.getAllEvents()


    suspend fun insert(event: Event){
        dao.insertEvent(event)
    }

    suspend fun update(event: Event){
        dao.updateEvent(event)
    }

    suspend fun delete(event: Event){
        dao.deleteEvent(event)
    }

    suspend fun getEventsByDate(date: LocalDate): List<Event> {
        return dao.getEventsByDate(date)
    }
}
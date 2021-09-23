package com.example.eventcalendar.model


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
}
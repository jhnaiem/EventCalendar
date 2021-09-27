package com.example.eventcalendar.presentation.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.eventcalendar.model.Event
import com.example.eventcalendar.model.EventRepo
import kotlinx.coroutines.launch
import java.time.LocalDate


class EventViewModel(private val repo: EventRepo) : ViewModel() {

    val events = repo.events


    @RequiresApi(Build.VERSION_CODES.O)
    fun saveOrUpdate(event: Event, saveOrUpdateToggle: Boolean) {

        if (saveOrUpdateToggle) {
            insertEvent(event)

        } else {
            updateEvent(event)
        }
    }

    fun deleteOrCancel(event: Event, deleteOrCancelToggle: Boolean) {
        if (!deleteOrCancelToggle) {
            deleteEvent(event)
        }
    }

    suspend fun getDateWiseEventList(date: LocalDate): List<Event> {
        return repo.getEventsByDate(date)
    }

    private fun insertEvent(event: Event) {
        viewModelScope.launch {
            repo.insert(event)
        }

    }

    private fun updateEvent(event: Event) {
        viewModelScope.launch {
            repo.update(event)
        }

    }

    private fun deleteEvent(event: Event) {
        viewModelScope.launch {
            repo.delete(event)
        }

    }

}
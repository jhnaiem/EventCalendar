package com.example.eventcalendar.presentation.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.*
import com.example.eventcalendar.model.Event
import com.example.eventcalendar.model.EventRepo
import kotlinx.coroutines.launch
import java.time.LocalDate


class EventViewModel(private val repo: EventRepo) : ViewModel() {

    private lateinit var eventToUpdateOrDelete: Event
    private var isUpdateOrDelete = false
    val events = repo.events


    @RequiresApi(Build.VERSION_CODES.O)
    fun saveOrUpdate(event: Event, saveOrUpdateToggle: Boolean) {

        if (saveOrUpdateToggle) {
            insert(event)

        } else {
            update(event)
        }
    }

    fun getDateWiseEventList(date: LocalDate): LiveData<List<Event>> {
        return repo.getEventsByDate(date)
    }

    private fun insert(event: Event) {
        viewModelScope.launch {
            repo.insert(event)
        }

    }

    private fun update(event: Event) {
        viewModelScope.launch {
            repo.update(event)
        }

    }

}
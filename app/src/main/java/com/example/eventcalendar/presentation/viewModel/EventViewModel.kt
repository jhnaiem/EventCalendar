package com.example.eventcalendar.presentation.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.databinding.Bindable
import androidx.lifecycle.*
import androidx.lifecycle.Observer
import com.example.eventcalendar.model.Event
import com.example.eventcalendar.model.EventRepo
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*


class EventViewModel(private val repo: EventRepo) : ViewModel() {

    private lateinit var eventToUpdateOrDelete: Event
    private var isUpdateOrDelete = false
    val events = repo.events



    val inputTitle = MutableLiveData<String?>()

    val inputDesc = MutableLiveData<String?>()


    @RequiresApi(Build.VERSION_CODES.O)
    fun saveOrUpdate(selectedDate: LocalDate) {

        if (isUpdateOrDelete) {

        } else {
            val title: String = inputTitle.value!!
            val desc: String = inputDesc.value!!
            insert(Event(0,selectedDate, LocalDateTime.now(), LocalDateTime.now(), title, desc))
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

}
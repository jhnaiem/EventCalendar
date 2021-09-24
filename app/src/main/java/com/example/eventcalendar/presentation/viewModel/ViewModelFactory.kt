package com.example.eventcalendar.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.eventcalendar.model.EventRepo


class ViewModelFactory(private val repository: EventRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom((EventViewModel::class.java))){
            return EventViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown viewModel Class")
    }
}
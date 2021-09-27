package com.example.eventcalendar.presentation

import com.example.eventcalendar.model.Event


interface DialogButtonListener {
        fun onSaveButtonClick(event: Event,actionToggle:Boolean)
        fun onCancelButtonClick(event: Event,actionToggle:Boolean)
}
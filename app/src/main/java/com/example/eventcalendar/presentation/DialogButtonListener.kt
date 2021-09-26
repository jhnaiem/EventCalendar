package com.example.eventcalendar.presentation

import com.example.eventcalendar.model.Event
import java.time.LocalDate


interface DialogButtonListener {


        fun onSaveButtonClick(event: Event,actionToggle:Boolean)
        fun onCancelButtonClick()
}
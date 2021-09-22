package com.example.eventcalendar.presentation.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.ArrayList

/**
 Provide utils to show weekly calendar
**/

object CalendarUtils {

    @RequiresApi(Build.VERSION_CODES.O)
    var selectedDate: LocalDate = LocalDate.now()

    @RequiresApi(Build.VERSION_CODES.O)
    fun monthYearFromDate(date: LocalDate): String? {
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy")
        return date.format(formatter)
    }

    //To get days list of a week
    @RequiresApi(Build.VERSION_CODES.O)
    fun daysInWeekList(selectedDate: LocalDate): ArrayList<LocalDate> {
        val days = ArrayList<LocalDate>()
        var current: LocalDate = sundayForStartDate(selectedDate)
        val endDate = current.plusWeeks(1)
        while (current.isBefore(endDate)) {
            days.add(current)
            current = current.plusDays(1)
        }
        return days
    }

    //To set sunday as start day of a week and find the start day of the week of current date.
    @RequiresApi(Build.VERSION_CODES.O)
    private fun sundayForStartDate(current: LocalDate): LocalDate {
        var current = current
        val oneWeekAgo = current.minusWeeks(1)
        while (current.isAfter(oneWeekAgo)) {
            if (current.dayOfWeek == DayOfWeek.SUNDAY)
                return current
            current = current.minusDays(1)
        }
        return current
    }

}
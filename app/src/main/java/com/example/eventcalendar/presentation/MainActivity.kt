package com.example.eventcalendar.presentation

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.example.eventcalendar.R
import com.example.eventcalendar.presentation.utils.CalendarUtils
import com.example.eventcalendar.presentation.utils.CalendarUtils.daysInWeekList
import com.example.eventcalendar.presentation.utils.CalendarUtils.monthYearFromDate
import com.example.eventcalendar.databinding.ActivityMainBinding
import com.example.eventcalendar.presentation.adapter.WeeklyCalendarAdapter
import java.time.LocalDate
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: WeeklyCalendarAdapter


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initRecyclerView()


    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initRecyclerView() {
        binding.monthYearTV.text = monthYearFromDate(CalendarUtils.selectedDate)
        val days: ArrayList<LocalDate> = daysInWeekList(CalendarUtils.selectedDate)

        binding.calendarRecyclerView.layoutManager = GridLayoutManager(applicationContext,1,GridLayoutManager.HORIZONTAL,false)
        adapter = WeeklyCalendarAdapter()
        binding.calendarRecyclerView.adapter = adapter
        adapter.setList(days)

    }
}
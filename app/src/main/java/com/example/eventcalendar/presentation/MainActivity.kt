package com.example.eventcalendar.presentation

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.eventcalendar.R
import com.example.eventcalendar.presentation.utils.CalendarUtils
import com.example.eventcalendar.presentation.utils.CalendarUtils.daysInWeekList
import com.example.eventcalendar.presentation.utils.CalendarUtils.monthYearFromDate
import com.example.eventcalendar.databinding.ActivityMainBinding
import com.example.eventcalendar.model.Event
import com.example.eventcalendar.model.EventDatabase
import com.example.eventcalendar.model.EventRepo
import com.example.eventcalendar.presentation.adapter.WeeklyCalendarAdapter
import com.example.eventcalendar.presentation.viewModel.EventViewModel
import com.example.eventcalendar.presentation.viewModel.ViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.util.ArrayList

class MainActivity : AppCompatActivity(), DialogButtonListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: WeeklyCalendarAdapter
    private lateinit var eventViewModel: EventViewModel

    private var hashMapDateEvent: HashMap<LocalDate, Event> =
        HashMap<LocalDate, Event>() //define empty hashmap
    private var days: ArrayList<LocalDate> = ArrayList<LocalDate>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val dao = EventDatabase.getInstance(application).eventDao
        val eventViewModelFactory = ViewModelFactory(EventRepo(dao))
        eventViewModel = ViewModelProvider(this, eventViewModelFactory)[EventViewModel::class.java]

        binding.btnNext.setOnClickListener(View.OnClickListener {
            showNextWeek()
        })

        binding.btnPrev.setOnClickListener(View.OnClickListener {
            showPrevWeek()
        })

        days = daysInWeekList(CalendarUtils.selectedDate)
        initRecyclerView()
        getDateWiseEventList(days)

        eventViewModel.events.observe(this, Observer {
            getDateWiseEventList(days)
        })

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initRecyclerView() {
        binding.monthYearTV.text = monthYearFromDate(CalendarUtils.selectedDate)
        binding.calendarRecyclerView.layoutManager =
            GridLayoutManager(applicationContext, 1, GridLayoutManager.HORIZONTAL, false)
        adapter =
            WeeklyCalendarAdapter { selectedDate: LocalDate, clickToggle: Boolean, selectedEvent: Event
                ->
                addBtnOrItemClicked(selectedDate, clickToggle, selectedEvent)
            }
        binding.calendarRecyclerView.adapter = adapter
        adapter.setList(days, hashMapDateEvent)

    }

    //To popup the dialog and add or update event
    @RequiresApi(Build.VERSION_CODES.O)
    private fun addBtnOrItemClicked(
        selectedDate: LocalDate,
        clickToggle: Boolean,
        selectedEvent: Event
    ) {
        val addEventDialog = AddEventDialog(selectedDate, clickToggle, selectedEvent)
        addEventDialog.show(supportFragmentManager, "TAG")
        addEventDialog.observeClick(this)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun showNextWeek() {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.plusWeeks(1)
        binding.monthYearTV.text = monthYearFromDate(CalendarUtils.selectedDate)
        days = daysInWeekList(CalendarUtils.selectedDate)
        getDateWiseEventList(days)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun showPrevWeek() {
        CalendarUtils.selectedDate = CalendarUtils.selectedDate.minusWeeks(1)
        binding.monthYearTV.text = monthYearFromDate(CalendarUtils.selectedDate)
        days.clear()
        hashMapDateEvent.clear()
        days = daysInWeekList(CalendarUtils.selectedDate)
        getDateWiseEventList(days)


    }


    private fun getDateWiseEventList(days: ArrayList<LocalDate>) {
        CoroutineScope(Dispatchers.IO).launch {
            for (day in days) {
                val result = eventViewModel.getDateWiseEventList(day)
                if (result.isNotEmpty()) {
                    hashMapDateEvent.put(day, result[0])
                }
            }

            withContext(Dispatchers.Main) {
                adapter.setList(days, hashMapDateEvent)
                adapter.notifyDataSetChanged()
            }
        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onSaveButtonClick(event: Event, actionToggle: Boolean) {
        eventViewModel.saveOrUpdate(event, actionToggle)
    }

    override fun onCancelButtonClick(event: Event, actionToggle: Boolean) {
        eventViewModel.deleteOrCancel(event, actionToggle)
    }


}
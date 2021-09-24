package com.example.eventcalendar.presentation.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.eventcalendar.R
import com.example.eventcalendar.presentation.adapter.WeeklyCalendarAdapter.MyViewHolder
import com.example.eventcalendar.databinding.DateCellBinding
import com.example.eventcalendar.model.DateEvent
import com.example.eventcalendar.model.Event
import java.time.LocalDate


class WeeklyCalendarAdapter(private val clickListener: (LocalDate) -> Unit) : RecyclerView.Adapter<MyViewHolder>() {

    private var daysList: List<LocalDate> = ArrayList<LocalDate>()
    private var eventList: List<Event> = ArrayList<Event>()
    private var dateWiseEventList: List<DateEvent> = ArrayList<DateEvent>()
    private var hashMapDateEventList:HashMap<LocalDate,List<Event>> = HashMap<LocalDate,List<Event>>() //define empty hashmap



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<DateCellBinding>(
            layoutInflater,
            R.layout.date_cell,
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(daysList[position], hashMapDateEventList.get(daysList[position])!!,clickListener)
    }

    override fun getItemCount(): Int {
        return daysList.size
    }

    fun setList(days: List<LocalDate>, dateEventList: HashMap<LocalDate, List<Event>>){
        daysList = days
        hashMapDateEventList = dateEventList
    }

    class MyViewHolder(private val binding: DateCellBinding) : RecyclerView.ViewHolder(binding.root) {
        private val childAdapter: EventAdapter = EventAdapter()

        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(
            date: LocalDate,
            dateWiseEventList: List<Event>,
            clickListener: (LocalDate) -> Unit
        ) {
            if (date == null) {
                binding.cellDayText.text = ""
            } else {
                binding.dayOfWeekText.text = date.dayOfWeek.toString().subSequence(0,3)
                binding.cellDayText.text = date.dayOfMonth.toString()
                childAdapter.setEventList(dateWiseEventList)
                childAdapter.notifyDataSetChanged()
                binding.btnAddEvent.setOnClickListener(View.OnClickListener {
                    clickListener(date)
                })

            }

        }


    }
}
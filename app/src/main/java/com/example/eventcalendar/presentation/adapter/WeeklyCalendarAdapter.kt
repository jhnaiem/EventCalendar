package com.example.eventcalendar.presentation.adapter

import android.annotation.SuppressLint
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
import com.example.eventcalendar.model.Event
import kotlinx.android.synthetic.main.event_item.view.*
import java.time.LocalDate
import java.time.LocalDateTime


class WeeklyCalendarAdapter(private val clickListener: (LocalDate, Boolean, Event) -> Unit) :
    RecyclerView.Adapter<MyViewHolder>() {


    private var daysList: List<LocalDate> = ArrayList<LocalDate>()
    private var event: Event? = null
    private var hashMapDateEvent: HashMap<LocalDate, Event> =
        HashMap<LocalDate, Event>() //define empty hashmap


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

        if (hashMapDateEvent[daysList[position]] == null)
            holder.bind(daysList[position], event, clickListener)
        else
            holder.bind(
                daysList[position],
                hashMapDateEvent[daysList[position]]!!, clickListener
            )


    }

    override fun getItemCount(): Int {
        return daysList.size
    }

    fun setList(
        days: ArrayList<LocalDate>,
        dateEvent: HashMap<LocalDate, Event>
    ) {
        daysList = days
        hashMapDateEvent = dateEvent

    }

    class MyViewHolder(private val binding: DateCellBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private var addButtonToggle = true

        @SuppressLint("NotifyDataSetChanged")
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(
            date: LocalDate,
            dateWiseEvent: Event?,
            clickListener: (LocalDate, Boolean, Event) -> Unit
        ) {
            if (date == null) {
                binding.cellDayText.text = ""
            } else {
                binding.dayOfWeekText.text = date.dayOfWeek.toString().subSequence(0, 3)
                binding.cellDayText.text = date.dayOfMonth.toString()
                if (dateWiseEvent != null) {
                    addButtonToggle = false
                    binding.parentView.card_view.visibility = View.VISIBLE
                    binding.eventItemView.titleTextView.text = dateWiseEvent.title
                    binding.eventItemView.descTextView.text = dateWiseEvent.description
                    binding.btnAddEvent.visibility = View.GONE
                    binding.parentView.card_view.setOnClickListener(View.OnClickListener {
                        clickListener(date, addButtonToggle, dateWiseEvent)
                    })
                } else
                    binding.btnAddEvent.setOnClickListener(View.OnClickListener {
                        clickListener(date, addButtonToggle,Event(-1, LocalDate.MIN, LocalDateTime.MIN,
                            LocalDateTime.MIN,"",""))
                    })

            }

        }


    }
}
package com.example.eventcalendar.presentation.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.eventcalendar.R
import com.example.eventcalendar.presentation.adapter.WeeklyCalendarAdapter.MyViewHolder
import com.example.eventcalendar.databinding.DateCellBinding
import java.time.LocalDate


class WeeklyCalendarAdapter() : RecyclerView.Adapter<MyViewHolder>() {

    private var daysList: List<LocalDate> = ArrayList<LocalDate>()


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
        holder.bind(daysList[position])
    }

    override fun getItemCount(): Int {
        return daysList.size
    }

    fun setList(days : List<LocalDate>){
        daysList = days
    }

    class MyViewHolder(val binding: DateCellBinding) : RecyclerView.ViewHolder(binding.root) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun bind(date: LocalDate) {
            if (date == null) {
                binding.cellDayText.text = ""
            } else {
                binding.dayOfWeekText.text = date.dayOfWeek.toString().subSequence(0,3)
                binding.cellDayText.text = date.dayOfMonth.toString()

            }

        }


    }
}
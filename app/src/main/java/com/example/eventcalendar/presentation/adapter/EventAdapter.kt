package com.example.eventcalendar.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.eventcalendar.R
import com.example.eventcalendar.databinding.DateCellBinding
import com.example.eventcalendar.databinding.EventItemBinding
import com.example.eventcalendar.model.Event
import java.time.LocalDate


class EventAdapter() : RecyclerView.Adapter<EventAdapter.MyEventViewHolder>() {

    private var eventList: List<Event> = ArrayList<Event>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyEventViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<EventItemBinding>(
            layoutInflater,
            R.layout.event_item,
            parent,
            false
        )
        return MyEventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyEventViewHolder, position: Int) {
        holder.bind(eventList[position])
    }

    override fun getItemCount(): Int {
        return eventList.size
    }

    fun setEventList(events: List<Event>){
        eventList = events
    }


    class MyEventViewHolder(private val binding: EventItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(event: Event) {
            binding.titleTextView.text = event.title
            binding.descTextView.text = event.description
        }

    }
}
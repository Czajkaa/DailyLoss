package com.example.dailyloss

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class DayAdapter(
    private var days: List<Day>,
    private var selectedDate: Date?,
    private val onDayClick: (Date) -> Unit
) : RecyclerView.Adapter<DayAdapter.DayViewHolder>() {

    class DayViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val dayName: TextView = view.findViewById(R.id.dayName)
        val dayNumber: TextView = view.findViewById(R.id.dayNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_day, parent, false)
        return DayViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: DayViewHolder, position: Int) {
        val day = days[position]
        holder.dayName.text = day.name
        holder.dayNumber.text = day.number.toString()

        val context = holder.itemView.context

        holder.dayNumber.isSelected = isSameDay(day.date, selectedDate)
        val isToday = isSameDay(day.date, Calendar.getInstance().time)

        if (isToday) {
            holder.dayNumber.background = ContextCompat.getDrawable(context, R.drawable.day_today)
        } else {
            holder.dayNumber.background = ContextCompat.getDrawable(context, R.drawable.day_selected)
        }

        holder.itemView.setOnClickListener {
            val previousSelectedDate = selectedDate
            selectedDate = day.date
            notifyDataSetChanged()
            onDayClick(day.date)
            if (previousSelectedDate != null) {
                notifyItemChanged(days.indexOfFirst { isSameDay(it.date, previousSelectedDate) })
            }
        }
    }

    override fun getItemCount(): Int = days.size

    private fun isSameDay(date1: Date?, date2: Date?): Boolean {
        if (date1 == null || date2 == null) return false
        val calendar1 = Calendar.getInstance()
        val calendar2 = Calendar.getInstance()
        calendar1.time = date1
        calendar2.time = date2
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
                calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateDays(newDays: List<Day>, selectedDate: Date?) {
        this.days = newDays
        this.selectedDate = selectedDate
        notifyDataSetChanged()
    }
}
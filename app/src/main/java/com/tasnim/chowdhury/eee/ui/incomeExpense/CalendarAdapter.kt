package com.tasnim.chowdhury.eee.ui.incomeExpense

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.databinding.CalendarCelBinding
import java.time.LocalDate

class CalendarAdapter(dayOfMonth: ArrayList<String>, val currentDate: LocalDate): RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    val dayOfMonth: ArrayList<String>

    init {
        this.dayOfMonth = dayOfMonth
    }

    inner class CalendarViewHolder(val binding: CalendarCelBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            binding.cCellDayText.text = dayOfMonth[position]

            /*val itemDate = LocalDate.of(
                currentDate.year, currentDate.month, dayOfMonth[position].toInt()
            )
            if (itemDate == currentDate) {
                binding.cCellDayText.setBackgroundResource(R.drawable.current_date_bg)
                binding.cCellDayText.setTextColor(ContextCompat.getColor(binding.root.context, R.color.offWhite))
            } else {
                binding.cCellDayText.setBackgroundResource(R.drawable.calendar_cell_layout_bg)
                binding.cCellDayText.setTextColor(ContextCompat.getColor(binding.root.context, R.color.others_color))
            }*/
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalendarViewHolder {
        return CalendarViewHolder(
            CalendarCelBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int {
        return dayOfMonth.size
    }

    override fun onBindViewHolder(holder: CalendarViewHolder, position: Int) {
        holder.bind(position)
    }

}
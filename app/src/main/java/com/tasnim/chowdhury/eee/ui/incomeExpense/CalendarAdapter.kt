package com.tasnim.chowdhury.eee.ui.incomeExpense

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.databinding.CalendarCelBinding
import java.time.LocalDate
import java.util.Calendar

class CalendarAdapter(dayOfMonth: ArrayList<String>, val currentDate: LocalDate): RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    val dayOfMonth: ArrayList<String>

    private var todayPosition = -1

    init {
        this.dayOfMonth = dayOfMonth
    }

    inner class CalendarViewHolder(val binding: CalendarCelBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int) {
            binding.cCellDayText.text = dayOfMonth[position]

            val thisMonthDates = getCurrentMonthDates()

            if (dayOfMonth[position] == currentDate.dayOfMonth.toString() &&
                thisMonthDates.any { it[Calendar.DAY_OF_MONTH] == currentDate.dayOfMonth && it[Calendar.MONTH] == currentDate.monthValue - 1 }) {
                // Highlight the background for today's date
                binding.cCellDayText.setBackgroundResource(R.drawable.education_cat_bg)
            } else {
                // Reset the background for other dates
                binding.cCellDayText.setBackgroundResource(R.drawable.calendar_cell_layout_bg)
            }

            Log.d("chkDate", "$dayOfMonth $thisMonthDates")
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

    fun getCurrentMonthDates(): List<Calendar> {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)

        // Set the calendar to the first day of the current month
        calendar.set(currentYear, currentMonth, 1)

        val dates = mutableListOf<Calendar>()

        // Loop through the days of the current month
        while (calendar.get(Calendar.MONTH) == currentMonth) {
            val date = calendar.clone() as Calendar
            dates.add(date)
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        return dates
    }

    fun getTodayPosition(): Int {
        return todayPosition
    }

}
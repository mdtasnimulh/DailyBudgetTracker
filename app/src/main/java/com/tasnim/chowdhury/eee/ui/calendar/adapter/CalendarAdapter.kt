package com.tasnim.chowdhury.eee.ui.calendar.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.databinding.CalendarCelBinding
import com.tasnim.chowdhury.eee.ui.calendar.data.model.CalendarDate
import java.time.LocalDate
import java.util.Calendar

class CalendarAdapter(dayOfMonth: ArrayList<CalendarDate>, val currentDate: LocalDate): RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder>() {

    val dayOfMonth: ArrayList<CalendarDate>

    private var todayPosition = -1
    var dateClick:((date: CalendarDate, selectedItemPosition: Int) -> Unit)? = null

    var selectedItemPosition = RecyclerView.NO_POSITION

    init {
        this.dayOfMonth = dayOfMonth
    }

    inner class CalendarViewHolder(val binding: CalendarCelBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val calendarDate = dayOfMonth[position]
            val day = calendarDate.day
            val month = calendarDate.month
            val week = calendarDate.dayName
            val year = calendarDate.year

            binding.cCellDayText.text = day.toString()
            binding.cEventDot.visibility = View.GONE

            val isCurrentMonth = month == currentDate.monthValue
            val isCurrentDate = day == currentDate.dayOfMonth
            val isCurrentCalendarMonth = month == Calendar.getInstance().get(Calendar.MONTH) + 1

            if (isCurrentMonth && isCurrentDate && isCurrentCalendarMonth) {
                // Highlight the background for today's date in the current month
                binding.dateCl.setBackgroundResource(R.drawable.current_date_bg)
                binding.cCellDayText.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
            } else {
                if (!isCurrentMonth){
                    // Set the background for dates that showing inside this month but not current month dates
                    binding.dateCl.setBackgroundResource(R.drawable.not_month_date_bg)
                    binding.cCellDayText.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.transparentSilver
                        )
                    )
                }else {
                    // Set the background for weekend dates
                    if (week == "FRIDAY" || week == "SATURDAY"){
                        binding.dateCl.setBackgroundResource(R.drawable.weekend_date_bg)
                        binding.cCellDayText.setTextColor(
                            ContextCompat.getColor(
                                binding.root.context,
                                R.color.roseRed
                            )
                        )
                    }else {
                        // Reset the background for other dates
                        binding.dateCl.setBackgroundResource(R.drawable.calendar_cell_layout_bg)
                        binding.cCellDayText.setTextColor(
                            ContextCompat.getColor(
                                binding.root.context,
                                R.color.others_color
                            )
                        )
                    }
                }
            }

            // Check if this item is the currently selected item
            val isSelected = position == selectedItemPosition
            // Set the background based on whether it's selected
            if (isSelected) {
                // Set the background for the selected item and text color of the selected date
                binding.dateCl.setBackgroundResource(R.drawable.selected_date_bg)
                binding.cCellDayText.setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
            }


            binding.dateCl.setOnClickListener {
                // Update the selected item position
                selectedItemPosition = position
                // Notify the adapter that the data has changed (to refresh backgrounds)
                notifyDataSetChanged()
                // Handle item click here
                dateClick?.invoke(calendarDate,selectedItemPosition)
            }
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
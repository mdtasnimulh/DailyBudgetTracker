package com.tasnim.chowdhury.eee.ui.incomeExpense

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.tasnim.chowdhury.eee.databinding.FragmentCalendarViewBinding
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

class CalendarViewFragment : Fragment() {

    private lateinit var binding: FragmentCalendarViewBinding
    private var currentDate = LocalDate.now()
    private var cAdapter: CalendarAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCalendarViewBinding.inflate(inflater, container, false).also{
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentDate = LocalDate.now()
        setMonthView()

        binding.cPreviousMonth.setOnClickListener {
            previousMonthAction()
        }
        binding.cForwardMonth.setOnClickListener {
            nextMonthAction()
        }

        // Get today's position
        val todayPosition = cAdapter?.getTodayPosition() ?: -1
        // Scroll to today's position if it's valid
        if (todayPosition >= 0) {
            binding.cRV.scrollToPosition(todayPosition)
        }
    }

    override fun onResume() {
        super.onResume()

        handleBackPressed()
    }

    private fun setMonthView() {
        binding.cMonthYearText.text = monthYearFromDate(currentDate)
        val daysInMonth: ArrayList<CalendarDate> = daysInMonthArray(currentDate)

        cAdapter = CalendarAdapter(daysInMonth, currentDate)
        binding.cRV.adapter = cAdapter
        binding.cRV.layoutManager = GridLayoutManager(requireContext(), 7)
    }

    private fun daysInMonthArray(date: LocalDate): ArrayList<CalendarDate> {
        val daysInMonthArray: ArrayList<CalendarDate> = arrayListOf()
        val yearMonth: YearMonth = YearMonth.from(date)
        val daysInMonth: Int = yearMonth.lengthOfMonth()
        val firstOfMonth: LocalDate = date.withDayOfMonth(1)

        // Calculate the number of days to show from the previous month.
        val dayOfWeek: Int = firstOfMonth.dayOfWeek.value
        val daysFromPrevMonth = if (dayOfWeek == 7) 0 else dayOfWeek
        val prevMonth = date.minusMonths(1)
        val daysInPrevMonth = YearMonth.from(prevMonth).lengthOfMonth()

        // Add days from the previous month.
        for (i in (daysInPrevMonth - daysFromPrevMonth + 1)..daysInPrevMonth) {
            daysInMonthArray.add(CalendarDate(i, prevMonth.monthValue, ""))
        }

        // Add days from the current month.
        for (i in 1..daysInMonth) {
            val dayOfWeekName = calculateDayOfWeekName(date.withDayOfMonth(i))
            daysInMonthArray.add(CalendarDate(i, date.monthValue, dayOfWeekName))
        }

        // Calculate the number of days to show from the next month.
        val totalDays = daysFromPrevMonth + daysInMonth
        val daysFromNextMonth = 42 - totalDays
        val nextMonth = date.plusMonths(1)

        // Add days from the next month.
        for (i in 1..daysFromNextMonth) {
            daysInMonthArray.add(CalendarDate(i, nextMonth.monthValue, ""))
        }

        return daysInMonthArray
    }

    private fun calculateDayOfWeekName(date: LocalDate): String {
        val dayOfWeek = date.dayOfWeek
        return dayOfWeek.toString()
    }

    private fun monthYearFromDate(date: LocalDate): String{
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM, yy")
        return date.format(formatter)
    }

    private fun previousMonthAction(){
        currentDate = currentDate.minusMonths(1)
        setMonthView()
    }

    private fun nextMonthAction(){
        currentDate = currentDate.plusMonths(1)
        setMonthView()
    }

    private fun handleBackPressed(){
        val callBack = object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().navigateUp()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callBack)
    }

}
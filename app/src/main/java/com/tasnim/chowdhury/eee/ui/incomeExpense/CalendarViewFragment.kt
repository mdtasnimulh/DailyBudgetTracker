package com.tasnim.chowdhury.eee.ui.incomeExpense

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.tasnim.chowdhury.eee.databinding.FragmentCalendarViewBinding
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Calendar

class CalendarViewFragment : Fragment() {

    private lateinit var binding: FragmentCalendarViewBinding
    private var currentDate = LocalDate.now()
    private var cAdapter: CalendarAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return FragmentCalendarViewBinding.inflate(inflater, container, false).also{
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendar = Calendar.getInstance()

        currentDate = LocalDate.now()
        setMonthView()

        binding.cPreviousMonth.setOnClickListener {
            previousMonthAction()
        }
        binding.cForwardMonth.setOnClickListener {
            nextMonthAction()
        }
    }

    private fun setMonthView() {
        binding.cMonthYearText.text = monthYearFromDate(currentDate)
        val daysInMonth: ArrayList<String> = daysInMonthArray(currentDate)

        cAdapter = CalendarAdapter(daysInMonth, currentDate)
        binding.cRV.adapter = cAdapter
        binding.cRV.layoutManager = GridLayoutManager(requireContext(), 7)
    }

    private fun daysInMonthArray(date: LocalDate): ArrayList<String> {
        val daysInMonthArray: ArrayList<String> = arrayListOf()
        val yearMonth: YearMonth = YearMonth.from(date)
        val daysInMonth: Int = yearMonth.lengthOfMonth()
        val firstOfMonth: LocalDate = currentDate.withDayOfMonth(1)
        val dayOfWeek: Int = firstOfMonth.dayOfWeek.value

        for (i in 1..42){
            if (i <= dayOfWeek || i > (daysInMonth + dayOfWeek)){
                daysInMonthArray.add("")
            }else {
                daysInMonthArray.add((i - dayOfWeek).toString())
            }
        }

        return daysInMonthArray
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

}
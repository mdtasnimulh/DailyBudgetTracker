package com.tasnim.chowdhury.eee.ui.calendar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.ui.incomeExpense.data.viewModel.IncomeExpenseViewModel
import com.tasnim.chowdhury.eee.databinding.FragmentCalendarViewBinding
import com.tasnim.chowdhury.eee.ui.incomeExpense.adapter.MainFragmentAdapter
import com.tasnim.chowdhury.eee.ui.calendar.adapter.CalendarAdapter
import com.tasnim.chowdhury.eee.ui.calendar.data.model.CalendarDate
import com.tasnim.chowdhury.eee.ui.incomeExpense.data.model.IncomeExpense
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

class CalendarViewFragment : Fragment() {

    private lateinit var binding: FragmentCalendarViewBinding
    private var currentDate = LocalDate.now()
    private var cAdapter: CalendarAdapter? = null
    private var requiredDate = ""

    private lateinit var viewModel: IncomeExpenseViewModel
    private lateinit var dateDetailsAdapter: MainFragmentAdapter
    private var incomeExpenseList: ArrayList<IncomeExpense>? = null

    private var perDayIncome: Float = 0.0f
    private var perDayExpense: Float = 0.0f

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

        incomeExpenseList = arguments?.getSerializable("objectList")as? ArrayList<IncomeExpense>
        currentDate = LocalDate.now()
        binding.dateView.text = currentDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))
        setMonthView()

        setupAdapter()
        setupClicks()

        // Get today's position
        val todayPosition = cAdapter?.getTodayPosition() ?: -1
        // Scroll to today's position if it's valid
        if (todayPosition >= 0) {
            binding.cRV.scrollToPosition(todayPosition)
        }
    }

    private fun setupClicks() {
        binding.cPreviousMonth.setOnClickListener {
            previousMonthAction()
        }
        binding.cForwardMonth.setOnClickListener {
            nextMonthAction()
        }

        binding.toolBarBackIcon.setOnClickListener {
            findNavController().navigateUp()
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
        incomeExpenseList?.let { cAdapter?.setIncomeTransactions(it)}
        incomeExpenseList?.let { cAdapter?.setExpenseTransactions(it)}

        cAdapter?.dateClick = { dates, selectedItemPosition, perDayIncome, perDayExpense ->
            // Update the selected item position
            cAdapter?.selectedItemPosition = selectedItemPosition

            // Notify the adapter that the data has changed (to refresh backgrounds)
            cAdapter?.notifyDataSetChanged()

            val formattedDate = LocalDate.of(dates.year, dates.month, dates.day)
                .format(DateTimeFormatter.ofPattern("MMM dd, yyyy"))
            binding.dateView.text = formattedDate
            requiredDate = formattedDate

            initData(perDayIncome, perDayExpense)
            binding.calendarViewCl.requestFocus()
        }

        requiredDate = currentDate.format(DateTimeFormatter.ofPattern("MMM dd, yyy"))
        initData(perDayIncome, perDayExpense)
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
            daysInMonthArray.add(CalendarDate(i, prevMonth.monthValue, prevMonth.year, ""))
        }

        // Add days from the current month.
        for (i in 1..daysInMonth) {
            val dayOfWeekName = calculateDayOfWeekName(date.withDayOfMonth(i))
            daysInMonthArray.add(CalendarDate(i, date.monthValue, date.year, dayOfWeekName))
        }

        // Calculate the number of days to show from the next month.
        val totalDays = daysFromPrevMonth + daysInMonth
        val daysFromNextMonth = 42 - totalDays
        val nextMonth = date.plusMonths(1)

        // Add days from the next month.
        for (i in 1..daysFromNextMonth) {
            daysInMonthArray.add(CalendarDate(i, nextMonth.monthValue, nextMonth.year, ""))
        }

        return daysInMonthArray
    }

    private fun calculateDayOfWeekName(date: LocalDate): String {
        val dayOfWeek = date.dayOfWeek
        return dayOfWeek.toString()
    }

    private fun monthYearFromDate(date: LocalDate): String{
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM, yyyy")
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

    private fun setupAdapter() {
        dateDetailsAdapter = MainFragmentAdapter(requireContext(), "BudgetDetailsFragment")
        binding.dateDetailsRv.adapter = dateDetailsAdapter
        binding.dateDetailsRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.dateDetailsRv.setHasFixedSize(false)
        binding.dateDetailsRv.itemAnimator = DefaultItemAnimator()
    }

    private fun initData(perDayIncome: Float, perDayExpense: Float) {

        val fromDate = requiredDate

        viewModel = ViewModelProvider(this)[IncomeExpenseViewModel::class.java]

        viewModel.getAllIncomeExpense.observe(viewLifecycleOwner){ incomeExpense ->

            val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.US)

            if (incomeExpense.isNotEmpty()) {
                val sortedData = incomeExpense.filter { data ->
                    val transactionDate = Date(data.iEDate?.let { dateFormat.parse(it)?.time } ?: 0L)
                    val formattedTransactionDate = dateFormat.format(transactionDate) // Format the transactionDate

                    formattedTransactionDate == fromDate
                }

                val currentDayIncome = sortedData.filter { it.iEType == getString(R.string.income) }.sumByDouble { it.iEAmount ?: 0.0 }
                val currentDayExpense = sortedData.filter { it.iEType == getString(R.string.expense) }.sumByDouble { it.iEAmount ?: 0.0 }

                this.perDayIncome = currentDayIncome.toFloat()
                this.perDayExpense = currentDayExpense.toFloat()


                dateDetailsAdapter.addIncomeExpense(sortedData)

                if (sortedData.isNotEmpty()){
                    binding.noTransactionTv.visibility = View.GONE
                    binding.noTransactionView.visibility = View.GONE
                    binding.dateDetailsRv.visibility = View.VISIBLE
                }else {
                    binding.noTransactionTv.visibility = View.VISIBLE
                    binding.noTransactionView.visibility = View.VISIBLE
                    binding.dateDetailsRv.visibility = View.GONE
                }

                binding.IncomeValueTv.text = this.perDayIncome.toString()
                binding.expenseValueTv.text = this.perDayExpense.toString()
                if (currentDayIncome >= 1) binding.cViewIncomeCardLl.setBackgroundResource(R.drawable.c_income_bg) else binding.cViewIncomeCardLl.setBackgroundResource(R.drawable.c_amount_empty_bg)
                if (currentDayExpense >= 1) binding.cViewExpenseCardLl.setBackgroundResource(R.drawable.c_expense_bg) else binding.cViewExpenseCardLl.setBackgroundResource(R.drawable.c_amount_empty_bg)
            }

        }

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
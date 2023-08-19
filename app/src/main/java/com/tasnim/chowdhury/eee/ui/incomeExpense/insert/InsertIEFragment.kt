package com.tasnim.chowdhury.eee.ui.incomeExpense.insert

import android.os.Bundle
import android.text.format.DateFormat.is24HourFormat
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.data.model.IncomeExpense
import com.tasnim.chowdhury.eee.data.viewModel.IncomeExpenseViewModel
import com.tasnim.chowdhury.eee.databinding.FragmentInsertIEBinding
import com.tasnim.chowdhury.eee.ui.incomeExpense.CalculatorDialogFragment
import com.tasnim.chowdhury.eee.ui.incomeExpense.CalculatorDialogFragment.Companion.TAG
import com.tasnim.chowdhury.eee.ui.incomeExpense.CategoryChooseDialogFragment
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

class InsertIEFragment : Fragment(), IncomeExpenseListener {

    private lateinit var binding: FragmentInsertIEBinding
    private lateinit var viewModel: IncomeExpenseViewModel

    var year: Int = 0
    var month: Int = 0
    var dayOfMonth: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentInsertIEBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[IncomeExpenseViewModel::class.java]

        setupClicks()

    }

    override fun onResume() {
        super.onResume()

        setupTypeAdapter()
        handleBackPressed()
    }

    private fun setupTypeAdapter() {
        val type = resources.getStringArray(R.array.transaction_type)
        val typeAdapter = ArrayAdapter(requireContext(), R.layout.date_filter_dropdown, type)
        binding.type.setAdapter(typeAdapter)
    }

    private fun setupClicks(){
        binding.timeTil.setOnClickListener {
            openTimePickerDialog()
        }
        binding.dateTil.setOnClickListener {
            openDatePickerDialog()
        }

        binding.insertButton.setOnClickListener {
            insertIncomeExpense()
        }

        binding.amount.setOnClickListener {
            val dialogFragment = CalculatorDialogFragment()
            dialogFragment.setCalculatorResultListener(this)
            dialogFragment.show(childFragmentManager, TAG)
        }

        binding.category.setOnClickListener {
            val dialog = CategoryChooseDialogFragment()
            dialog.setCategoryTitleListener(this)
            dialog.show(childFragmentManager, TAG)
        }
    }

    private fun openDatePickerDialog() {
        val today = MaterialDatePicker.todayInUtcMilliseconds()
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))

        calendar.timeInMillis = today

        val constraintsBuilder =
            CalendarConstraints.Builder()
                .setOpenAt(calendar.timeInMillis)
                .setStart(0)

        val datePicker = MaterialDatePicker.Builder.datePicker()
            .setTitleText("Select Date")
            .setCalendarConstraints(constraintsBuilder.build())
            .setSelection(today)
            .build()
        datePicker.show(childFragmentManager, "DATE_TAG")

        val dateFormatter = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())

        datePicker.addOnPositiveButtonClickListener { selectedDate ->
            val dayMonthYear = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
            dayMonthYear.timeInMillis = selectedDate

            val formattedDate = dateFormatter.format(dayMonthYear.time)
            binding.dateTil.setText(formattedDate)

            datePicker.dismiss()
        }

        datePicker.addOnCancelListener {
            datePicker.dismiss()
        }
    }

    private fun openTimePickerDialog() {
        val isSystem24Hour = is24HourFormat(requireContext())
        val clockFormat = if (isSystem24Hour) TimeFormat.CLOCK_12H else TimeFormat.CLOCK_12H

        val currentTime = LocalDateTime.now()
        val initialHour = currentTime.format(DateTimeFormatter.ofPattern("HH")).toInt()
        val initialMinute = currentTime.format(DateTimeFormatter.ofPattern("MM")).toInt()

        val timePicker = MaterialTimePicker.Builder()
            .setTimeFormat(clockFormat)
            .setHour(initialHour)
            .setMinute(initialMinute)
            .setTitleText("Select Time")
            .build()
        timePicker.show(childFragmentManager, "TIME_TAG")

        timePicker.addOnPositiveButtonClickListener {
            val hour = timePicker.hour
            val minute = timePicker.minute
            val amPm = if (hour < 12) "AM" else "PM"
            val hour12 = if (isSystem24Hour || hour >= 12) hour - 12 else hour
            val formattedHour = if (hour12 == 0) 12 else hour12
            val pickedTime = String.format(Locale.getDefault(), "%02d:%02d %s", formattedHour, minute, amPm)
            binding.timeTil.setText(pickedTime)
            timePicker.dismiss()
        }
        timePicker.addOnNegativeButtonClickListener {
            timePicker.dismiss()
        }
        timePicker.addOnDismissListener {
            timePicker.dismiss()
        }
        timePicker.addOnCancelListener {
            timePicker.dismiss()
        }
    }

    private fun insertIncomeExpense() {
        val title = binding.title.text.toString()
        val type = binding.type.text.toString()
        val note = binding.note.text.toString()
        val time = binding.timeTil.text.toString()
        val date = binding.dateTil.text.toString()
        val amount = binding.amount.text.toString()
        val category = binding.category.text.toString()
        val paymentMethod = binding.paymentMethod.text.toString()

        if (binding.title.text.isNullOrEmpty() || binding.type.text.isNullOrEmpty() || binding.amount.text.isNullOrEmpty()){
            Toast.makeText(requireContext(), "Please fill all the required field.", Toast.LENGTH_SHORT).show()
            binding.title.requestFocus()
        }else{
            val incomeExpense = IncomeExpense(0, title, type, note, time, amount.toDouble(), category, date, paymentMethod)

            viewModel.insertIncomeExpense(incomeExpense)
            findNavController().popBackStack()

            Toast.makeText(requireContext(), "Successfully Added Income/Expense.", Toast.LENGTH_SHORT).show()
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

    override fun onCalculatorResultCalculated(result: String) {
        Log.d("chkData", result)
        binding.amount.setText(result)
    }

    override fun onCategoryClicked(categoryTitle: String) {
        binding.category.setText(categoryTitle)
    }
}
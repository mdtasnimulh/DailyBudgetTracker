package com.tasnim.chowdhury.eee.ui.budget.insert

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tasnim.chowdhury.eee.ui.budget.data.model.Budget
import com.tasnim.chowdhury.eee.ui.incomeExpense.data.viewModel.IncomeExpenseViewModel
import com.tasnim.chowdhury.eee.databinding.FragmentInsertBudgetBinding
import com.tasnim.chowdhury.eee.ui.incomeExpense.calculator.CalculatorDialogFragment
import com.tasnim.chowdhury.eee.ui.categoryDialogs.CategoryChooseDialogFragment
import com.tasnim.chowdhury.eee.ui.incomeExpense.insert.IncomeExpenseListener
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class InsertBudgetFragment : Fragment(), IncomeExpenseListener {

    private lateinit var binding: FragmentInsertBudgetBinding
    private lateinit var viewModel: IncomeExpenseViewModel

    private var categoryTitle: String = ""
    private var categoryParent: String = ""
    private var categoryIcon: Int = 0
    private var catIconBg: Int = 0

    private var dateFromOrTo = ""
    private var date: Date? = null
    private val formatter = SimpleDateFormat("MMM dd, yyyy", Locale.US)
    private var now = Calendar.getInstance()

    private val fromDateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
        now.set(Calendar.YEAR, year)
        now.set(Calendar.MONTH, monthOfYear)
        now.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        updateFromDate()

        // Update the minimum date for the "toDate" DatePickerDialog
        val calendar = Calendar.getInstance()
        calendar.set(year, monthOfYear, dayOfMonth)
        updateToDateMinDate(calendar)
    }

    private val toDateSetListener = DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
        now.set(Calendar.YEAR, year)
        now.set(Calendar.MONTH, monthOfYear)
        now.set(Calendar.DAY_OF_MONTH, dayOfMonth)
        updateToDate()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return FragmentInsertBudgetBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[IncomeExpenseViewModel::class.java]

        setupClicks()
        handleBackPressed()
    }

    private fun setupClicks() {

        binding.insertButton.setOnClickListener {
            insertIncomeExpense()
        }

        binding.amount.setOnClickListener {
            val dialogFragment = CalculatorDialogFragment()
            dialogFragment.setCalculatorResultListener(this)
            dialogFragment.show(childFragmentManager, CalculatorDialogFragment.TAG)
        }

        binding.category.setOnClickListener {
            val dialog = CategoryChooseDialogFragment()
            dialog.setCategoryTitleListener(this, "Budget")
            dialog.show(childFragmentManager, CalculatorDialogFragment.TAG)
        }

        binding.fromDateCl.setOnClickListener {
            if (binding.pointSystemFromDateEt.text.toString().isEmpty()) {
                pickFromDate(fromDateSetListener)
            } else {
                date = formatter.parse(binding.pointSystemFromDateEt.text.toString())
                pickFromDate(fromDateSetListener)
            }
        }
        binding.pointSystemFromDateEt.setOnClickListener {
            if (binding.pointSystemFromDateEt.text.toString().isEmpty()) {
                pickFromDate(fromDateSetListener)
            } else {
                date = formatter.parse(binding.pointSystemFromDateEt.text.toString())
                pickFromDate(fromDateSetListener)
            }
        }

        binding.toDateCl.setOnClickListener {
            if (binding.pointSystemToDateEt.text.toString().isEmpty()) {
                // If toDate field is empty, pick the date without a minimum date
                pickToDate(toDateSetListener, null)
            } else {
                // If toDate field has a value, parse the date and pass it as the minimum date
                date = formatter.parse(binding.pointSystemToDateEt.text.toString())
                pickToDate(toDateSetListener, Calendar.getInstance().apply { time = date!! })
            }
        }

        binding.pointSystemToDateEt.setOnClickListener {
            if (binding.pointSystemToDateEt.text.toString().isEmpty()) {
                // If toDate field is empty, pick the date without a minimum date
                pickToDate(toDateSetListener, null)
            } else {
                // If toDate field has a value, parse the date and pass it as the minimum date
                date = formatter.parse(binding.pointSystemToDateEt.text.toString())
                pickToDate(toDateSetListener, Calendar.getInstance().apply { time = date!! })
            }
        }

    }

    private fun updateToDateMinDate(minDate: Calendar) {
        binding.pointSystemToDateEt.setOnClickListener {
            pickToDate(toDateSetListener, minDate)
        }

        binding.toDateCl.setOnClickListener {
            pickToDate(toDateSetListener, minDate)
        }
    }

    private fun updateFromDate() {
        binding.pointSystemFromDateEt.setText(formatter.format(now.time))
        dateFromOrTo = binding.pointSystemFromDateEt.toString()
    }

    private fun updateToDate() {
        binding.pointSystemToDateEt.setText(formatter.format(now.time))
        dateFromOrTo = binding.pointSystemToDateEt.toString()
    }

    private fun pickFromDate(listener: DatePickerDialog.OnDateSetListener) {
        val calendar = Calendar.getInstance()
        try {
            date = formatter.parse(dateFromOrTo)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val dpd: DatePickerDialog
        val mYear = calendar.get(Calendar.YEAR) // current year
        val mMonth = calendar.get(Calendar.MONTH) // current month
        val mDay = calendar.get(Calendar.DAY_OF_MONTH) // current day

        if (date != null) {
            calendar.time = date
            dpd = DatePickerDialog(
                requireContext(),
                listener,
                // set DatePickerDialog to point to today's date when it loads up
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
        } else {
            dpd = DatePickerDialog(
                requireContext(),
                listener,
                // set DatePickerDialog to point to today's date when it loads up
                mYear,
                mMonth,
                mDay
            )
        }

        dpd.show()
    }
    private fun pickToDate(listener: DatePickerDialog.OnDateSetListener, minDate: Calendar?) {
        val calendar = Calendar.getInstance()

        try {
            date = formatter.parse(dateFromOrTo)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        val dpd: DatePickerDialog
        val mYear = calendar.get(Calendar.YEAR) // current year
        val mMonth = calendar.get(Calendar.MONTH) // current month
        val mDay = calendar.get(Calendar.DAY_OF_MONTH) // current day

        if (date != null) {
            calendar.time = date
            dpd = DatePickerDialog(
                requireContext(),
                listener,
                // set DatePickerDialog to point to today's date when it loads up
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
        } else {
            dpd = DatePickerDialog(
                requireContext(),
                listener,
                // set DatePickerDialog to point to today's date when it loads up
                mYear,
                mMonth,
                mDay
            )
        }

        // Set the minimum date if it is not null
        minDate?.let { dpd.datePicker.minDate = it.timeInMillis }

        dpd.show()
    }

    private fun insertIncomeExpense() {
        val title = binding.title.text.toString()
        val note = binding.note.text.toString()
        val amount = binding.amount.text.toString()
        val category = binding.category.text.toString()
        val categoryParent = categoryParent
        val categoryIcon = categoryIcon
        val catIconBg = catIconBg
        val startDate = binding.pointSystemFromDateEt.text.toString()
        val endDate = binding.pointSystemToDateEt.text.toString()
        val time = "Sep 19, 2023"

        if (binding.title.text.isNullOrEmpty() || binding.amount.text.isNullOrEmpty()){
            Toast.makeText(requireContext(), "Please fill all the required field.", Toast.LENGTH_SHORT).show()
            binding.title.requestFocus()
        }else{
            val budget = Budget(0, title, category, note, startDate, endDate, amount.toDouble(), categoryIcon, catIconBg, time)
            Log.d("chkDate", "$startDate $endDate insert")

            viewModel.insertBudget(budget)
            findNavController().popBackStack()

            Toast.makeText(requireContext(), "Successfully Created Budget.", Toast.LENGTH_SHORT).show()
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

    override fun onCategoryClicked(categoryTitle: String, categoryParent: String, categoryIcon: Int,catIconBg: Int) {
        this.categoryTitle = categoryTitle
        this.categoryParent = categoryParent
        this.categoryIcon = categoryIcon
        this.catIconBg = catIconBg
        binding.category.setText(categoryTitle)
        Log.d("chkData", "$categoryTitle $categoryParent $categoryIcon" )
    }

}
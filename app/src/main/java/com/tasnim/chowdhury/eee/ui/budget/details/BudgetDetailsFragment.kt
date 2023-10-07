package com.tasnim.chowdhury.eee.ui.budget.details

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.ui.incomeExpense.data.viewModel.IncomeExpenseViewModel
import com.tasnim.chowdhury.eee.databinding.FragmentBudgetDetailsBinding
import com.tasnim.chowdhury.eee.ui.utils.currencyType
import com.tasnim.chowdhury.eee.ui.incomeExpense.adapter.MainFragmentAdapter
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BudgetDetailsFragment : Fragment() {

    private lateinit var binding: FragmentBudgetDetailsBinding

    val budget by navArgs<BudgetDetailsFragmentArgs>()
    private lateinit var viewModel: IncomeExpenseViewModel
    private lateinit var adapter: MainFragmentAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return FragmentBudgetDetailsBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        initData()
        setupClicks()
        handleBackPressed()

    }

    private fun setupAdapter() {
        adapter = MainFragmentAdapter(requireContext(), "BudgetDetailsFragment")
        binding.budgetDetailsRv.adapter = adapter
        binding.budgetDetailsRv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.budgetDetailsRv.setHasFixedSize(false)
        binding.budgetDetailsRv.itemAnimator = DefaultItemAnimator()
    }

    private fun initData() {

        viewModel = ViewModelProvider(this)[IncomeExpenseViewModel::class.java]

        val budgetAmount = "$currencyType${budget.budgetDetails?.budgetAmount.toString()}"
        val budgetSpendAmount = "$currencyType${budget.spendAmount}"
        val remainingBalanceStart = "$currencyType${budget.budgetDetails?.budgetAmount?.minus(budget.spendAmount.toInt())}"

        binding.budgetTitle.text = budget.budgetDetails?.budgetTitle
        budget.budgetDetails?.budgetIcon?.let { binding.budgetIcon.setImageResource(it) }
        budget.budgetDetails?.budgetIconBg?.let { binding.budgetIcon.setBackgroundResource(it) }
        binding.budgetAmount.text = budgetAmount
        binding.budgetCategory.text = budget.budgetDetails?.budgetCategory
        binding.budgetStartDate.text = budget.budgetDetails?.budgetStartDate
        binding.budgetEndDate.text = budget.budgetDetails?.budgetEndDate
        binding.budgetSpendAmount.text = budgetSpendAmount
        binding.remainingBalanceStart.text = remainingBalanceStart

        setIconShadow(budget.budgetDetails?.budgetCategory.toString())

        viewModel.getAllIncomeExpense.observe(viewLifecycleOwner){ incomeExpense ->

            val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.US)
            val fromDate = budget.budgetDetails?.budgetStartDate?.let { dateFormat.parse(it)?.time } ?: 0L
            val toDate = budget.budgetDetails?.budgetEndDate?.let { dateFormat.parse(it)?.time } ?: 0L

            if (incomeExpense.isNotEmpty()) {
                val sortedData = incomeExpense.filter { data ->
                    val transactionDate = Date(data.iEDate?.let { dateFormat.parse(it)?.time } ?: 0L)
                    val startDate = Date(fromDate)
                    val endDate = Date(toDate)

                    transactionDate in startDate..endDate &&
                            data.categoryParent == budget.budgetDetails?.budgetCategory && data.iEType == "Expense"
                }

                adapter.addIncomeExpense(sortedData)
            }

        }

        binding.budgetProgressBar.max = budget.budgetDetails?.budgetAmount?.toInt() ?: 0
        binding.budgetProgressBar.min = 0
        binding.budgetProgressBar.progress = budget.spendAmount.toInt()

        val progressAnimator = ObjectAnimator.ofInt(
            binding.budgetProgressBar,
            "progress",
            0, budget.spendAmount.toInt()
        )
        progressAnimator?.duration = 800
        progressAnimator?.start()

    }

    private fun setupClicks() {

    }

    private fun setIconShadow(categoryParent: String){
        when (categoryParent) {
            "Food" -> {
                binding.budgetIcon.outlineAmbientShadowColor = ContextCompat.getColor(requireContext(), R.color.food_color)
                binding.budgetIcon.outlineSpotShadowColor = ContextCompat.getColor(requireContext(), R.color.food_color)
            }
            "Transportation" -> {
                binding.budgetIcon.outlineAmbientShadowColor = ContextCompat.getColor(requireContext(), R.color.transportation_color)
                binding.budgetIcon.outlineSpotShadowColor = ContextCompat.getColor(requireContext(), R.color.transportation_color)
            }
            "Housing/Rental" -> {
                binding.budgetIcon.outlineAmbientShadowColor = ContextCompat.getColor(requireContext(), R.color.housing_color)
                binding.budgetIcon.outlineSpotShadowColor = ContextCompat.getColor(requireContext(), R.color.housing_color)
            }
            "Entertainment" -> {
                binding.budgetIcon.outlineAmbientShadowColor = ContextCompat.getColor(requireContext(), R.color.entertainment_color)
                binding.budgetIcon.outlineSpotShadowColor = ContextCompat.getColor(requireContext(), R.color.entertainment_color)
            }
            "Healthcare/Family" -> {
                binding.budgetIcon.outlineAmbientShadowColor = ContextCompat.getColor(requireContext(), R.color.healthcare_color)
                binding.budgetIcon.outlineSpotShadowColor = ContextCompat.getColor(requireContext(), R.color.healthcare_color)
            }
            "Shopping" -> {
                binding.budgetIcon.outlineAmbientShadowColor = ContextCompat.getColor(requireContext(), R.color.shopping_color)
                binding.budgetIcon.outlineSpotShadowColor = ContextCompat.getColor(requireContext(), R.color.shopping_color)
            }
            "Education" -> {
                binding.budgetIcon.outlineAmbientShadowColor = ContextCompat.getColor(requireContext(), R.color.education_color)
                binding.budgetIcon.outlineSpotShadowColor = ContextCompat.getColor(requireContext(), R.color.education_color)
            }
            "Debt/Tax" -> {
                binding.budgetIcon.outlineAmbientShadowColor = ContextCompat.getColor(requireContext(), R.color.debt_color)
                binding.budgetIcon.outlineSpotShadowColor = ContextCompat.getColor(requireContext(), R.color.debt_color)
            }
            "Savings" -> {
                binding.budgetIcon.outlineAmbientShadowColor = ContextCompat.getColor(requireContext(), R.color.savings_color)
                binding.budgetIcon.outlineSpotShadowColor = ContextCompat.getColor(requireContext(), R.color.savings_color)
            }
            "Gifts/Donations" -> {
                binding.budgetIcon.outlineAmbientShadowColor = ContextCompat.getColor(requireContext(), R.color.gift_color)
                binding.budgetIcon.outlineSpotShadowColor = ContextCompat.getColor(requireContext(), R.color.gift_color)
            }
            "Travel" -> {
                binding.budgetIcon.outlineAmbientShadowColor = ContextCompat.getColor(requireContext(), R.color.travel_color)
                binding.budgetIcon.outlineSpotShadowColor = ContextCompat.getColor(requireContext(), R.color.travel_color)
            }
            "Others" -> {
                binding.budgetIcon.outlineAmbientShadowColor = ContextCompat.getColor(requireContext(), R.color.others_color)
                binding.budgetIcon.outlineSpotShadowColor = ContextCompat.getColor(requireContext(), R.color.others_color)
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
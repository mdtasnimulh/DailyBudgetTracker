package com.tasnim.chowdhury.eee.ui.incomeExpense.adapter

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.data.model.Budget
import com.tasnim.chowdhury.eee.data.viewModel.IncomeExpenseViewModel
import com.tasnim.chowdhury.eee.databinding.BudgetRvLayoutBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class BudgetAdapter(val context: Context, private val viewModel: IncomeExpenseViewModel): RecyclerView.Adapter<BudgetAdapter.BudgetViewHolder>() {

    private var budgetList: List<Budget> = listOf()

    inner class BudgetViewHolder(private val binding: BudgetRvLayoutBinding): RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(budget: Budget, position: Int){

            budget.budgetIcon?.let { binding.budgetIcon.setImageResource(it) }
            binding.budgetIcon.background = budget.budgetIconBg?.let { ContextCompat.getDrawable(context, it) }
            binding.budgetTitle.text = budget.budgetTitle
            binding.budgetCategory.text = budget.budgetCategory
            binding.budgetAmount.text = "৳ ${budget.budgetAmount.toString()}"
            binding.budgetStartDate.text = budget.budgetStartDate
            binding.budgetEndDate.text = budget.budgetEndDate

            // Calculate spend amount for this budget item
            val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.US)
            val fromDate = budget.budgetStartDate?.let { dateFormat.parse(it)?.time } ?: 0L
            val toDate = budget.budgetEndDate?.let { dateFormat.parse(it)?.time } ?: 0L

            viewModel.getAllIncomeExpense.observe(context as LifecycleOwner) { transactions ->
                if (transactions.isNotEmpty()) {
                    val amount = transactions.filter { transaction ->
                        //val transactionDate = transaction.iEDate?.let { dateFormat.parse(it)?.time } ?: 0L
                        val transactionDate = Date(transaction.iEDate?.let { dateFormat.parse(it)?.time } ?: 0L)
                        val startDate = Date(fromDate)
                        val endDate = Date(toDate)

                        // Compare transactionDate with startDate and endDate
                        transactionDate in startDate..endDate &&
                                transaction.iECategory == budget.budgetCategory && transaction.iEType == "Expense"
                    }.sumOf { it.iEAmount ?: 0.0 }

                    val amount75 = budget.budgetAmount?.div(4)?.times(3)
                    val amount50 = budget.budgetAmount?.div(4)?.times(2)
                    if (amount > amount75!!){
                        binding.budgetSpendAmount.setTextColor(ContextCompat.getColor(context, R.color.cherryRed))
                    }else if (amount > amount50!!){
                        binding.budgetSpendAmount.setTextColor(ContextCompat.getColor(context, R.color.bananaYellow))
                    }else {
                        binding.budgetSpendAmount.setTextColor(ContextCompat.getColor(context, R.color.leafGreen))
                    }

                    binding.budgetSpendAmount.text = "৳ $amount"
                    val remainingBalance = budget.budgetAmount.minus(amount)
                    binding.remainingBalanceStart.text = "৳ $remainingBalance"
                    binding.remainingBalanceEnd.text = "৳ ${budget.budgetAmount}"
                    binding.budgetProgressBar.max = budget.budgetAmount.toInt()
                    binding.budgetProgressBar.min = 0
                    binding.budgetProgressBar.progress = amount.toInt()

                    val progressAnimator = ObjectAnimator.ofInt(
                        binding.budgetProgressBar,
                        "progress",
                        0, amount.toInt()
                    )
                    progressAnimator?.duration = 1000
                    progressAnimator?.start()

                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BudgetViewHolder {
        return BudgetViewHolder(
            BudgetRvLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BudgetViewHolder, position: Int) {
        holder.bind(budgetList[position], position)
    }

    override fun getItemCount(): Int {
        return budgetList.size
    }

    fun addBudget(budget: List<Budget>){
        this.budgetList = budget
        notifyDataSetChanged()
    }

    fun addLimitedBudget(budget: List<Budget>) {
        this.budgetList = budget.take(7)
        notifyDataSetChanged()
    }

}
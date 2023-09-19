package com.tasnim.chowdhury.eee.ui.incomeExpense.adapter

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.data.model.Budget
import com.tasnim.chowdhury.eee.databinding.MainRvLayoutBinding
import com.tasnim.chowdhury.eee.data.model.IncomeExpense
import com.tasnim.chowdhury.eee.data.viewModel.IncomeExpenseViewModel
import com.tasnim.chowdhury.eee.databinding.BudgetRvLayoutBinding
import com.tasnim.chowdhury.eee.ui.MainFragmentDirections
import java.text.SimpleDateFormat
import java.util.Locale

class BudgetAdapter(val context: Context, private val viewModel: IncomeExpenseViewModel): RecyclerView.Adapter<BudgetAdapter.BudgetViewHolder>() {

    private var budgetList: List<Budget> = listOf()

    inner class BudgetViewHolder(private val binding: BudgetRvLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(budget: Budget, position: Int){

            budget.budgetIcon?.let { binding.budgetIcon.setImageResource(it) }
            binding.budgetIcon.background = budget.budgetIconBg?.let { ContextCompat.getDrawable(context, it) }
            binding.budgetTitle.text = budget.budgetTitle
            binding.budgetCategory.text = budget.budgetCategory
            binding.budgetAmount.text = "৳ ${budget.budgetAmount.toString()}"
            binding.budgetStartDate.text = budget.budgetStartDate
            binding.budgetEndDate.text = budget.budgetEndDate

            // Calculate spend amount for this budget item
            val dateFormat = SimpleDateFormat("MM/dd/yyyy", Locale.US)
            val fromDate = budget.budgetStartDate?.let { dateFormat.parse(it)?.time } ?: 0L
            val toDate = budget.budgetEndDate?.let { dateFormat.parse(it)?.time } ?: 0L

            if (fromDate != null) {
                if (toDate != null) {
                    viewModel.getAllDateFilteredTransaction(fromDate, toDate).observe(context as LifecycleOwner) { transactions ->

                        Log.d("chkValueOf", "$transactions fdasdf")

                        // Filter transactions by category
                        val categoryFilteredTransactions = transactions.filter { it.iECategory == budget.budgetCategory }
                        Log.d("chkValueOf", "$categoryFilteredTransactions")

                        // Calculate the sum of amounts for category-filtered transactions
                        val spendAmount = categoryFilteredTransactions.sumOf {it.iEAmount ?: 0.0}
                        Log.d("chkValueOf", "$spendAmount taka")

                        // Update the spendAmount field in the budget item
                        binding.remainingBalanceStart.text = spendAmount.toString()

                    }
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
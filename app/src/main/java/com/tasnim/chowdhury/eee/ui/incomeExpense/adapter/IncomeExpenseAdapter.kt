package com.tasnim.chowdhury.eee.ui.incomeExpense.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.databinding.MainRvLayoutBinding
import com.tasnim.chowdhury.eee.data.model.IncomeExpense
import com.tasnim.chowdhury.eee.ui.MainFragmentDirections
import kotlin.coroutines.coroutineContext

class IncomeExpenseAdapter(val context: Context): RecyclerView.Adapter<IncomeExpenseAdapter.IncomeExpenseViewHolder>() {

    private var incomeExpenseList: List<IncomeExpense> = listOf()

   inner class IncomeExpenseViewHolder(private val binding: MainRvLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(incomeExpense: IncomeExpense, position: Int){
            val amountPlus = "+${incomeExpense.iEAmount.toString()} Taka"
            val amountMinus = "-${incomeExpense.iEAmount.toString()} Taka"
            val amount = "${incomeExpense.iEAmount.toString()} Taka"
            binding.iconImgV.setImageResource(R.drawable.ic_chart)
            binding.titleTv.text = incomeExpense.iETitle
            binding.dateTv.text = incomeExpense.iEDate

            binding.mainRvLayout.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToIncomeExpenseUpdateFragment(incomeExpense)
                binding.mainRvLayout.findNavController().navigate(action)
            }

            when (incomeExpense.iEType) {
                "Income" -> {
                    binding.amountTv.text = amountPlus
                    binding.amountTv.setTextColor(Color.parseColor("#31D618"))
                }
                "Expense" -> {
                    binding.amountTv.text = amountMinus
                    binding.amountTv.setTextColor(Color.parseColor("#FA1E25"))
                }
                else -> {
                    binding.amountTv.text = amount
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeExpenseViewHolder {
        return IncomeExpenseViewHolder(
            MainRvLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: IncomeExpenseViewHolder, position: Int) {
        holder.bind(incomeExpenseList[position], position)
    }

    override fun getItemCount(): Int {
        return incomeExpenseList.size
    }

    // returning all records here
    fun addIncomeExpense(incomeExpense: List<IncomeExpense>){
        this.incomeExpenseList = incomeExpense
        notifyDataSetChanged()
    }

    // adding last 5 records for home fragment, returning only 5 item here
    fun addLimitedIncomeExpense(incomeExpense: List<IncomeExpense>) {
        this.incomeExpenseList = incomeExpense.take(7) // Limit to the first 7 items
        notifyDataSetChanged()
    }

}
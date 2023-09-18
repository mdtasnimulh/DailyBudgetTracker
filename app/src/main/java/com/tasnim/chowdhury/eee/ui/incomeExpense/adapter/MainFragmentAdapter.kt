package com.tasnim.chowdhury.eee.ui.incomeExpense.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.databinding.MainRvLayoutBinding
import com.tasnim.chowdhury.eee.data.model.IncomeExpense
import com.tasnim.chowdhury.eee.ui.MainFragmentDirections

class MainFragmentAdapter(val context: Context): RecyclerView.Adapter<MainFragmentAdapter.MainFragmentViewHolder>() {

    private var incomeExpenseList: List<IncomeExpense> = listOf()

    inner class MainFragmentViewHolder(private val binding: MainRvLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(incomeExpense: IncomeExpense, position: Int){
            val amountPlus = "+${incomeExpense.iEAmount.toString()} ৳"
            val amountMinus = "-${incomeExpense.iEAmount.toString()} ৳"
            val amount = "${incomeExpense.iEAmount.toString()} ৳"
            binding.iconImgV.setImageResource(incomeExpense.categoryIcon)
            binding.titleTv.text = incomeExpense.iETitle
            binding.dateTv.text = incomeExpense.iEDate
            binding.categoryTv.text = incomeExpense.iECategory

            binding.mainRvLayout.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToRecordDetailsFragment(incomeExpense)
                it.findNavController().navigate(action)
            }

            when (incomeExpense.iEType) {
                "Income" -> {
                    binding.amountTv.text = amountPlus
                    binding.amountTv.setTextColor(Color.parseColor("#31D618"))
                    binding.mainRvLayout.setBackgroundResource(R.drawable.background_income)
                }
                "Expense" -> {
                    binding.amountTv.text = amountMinus
                    binding.amountTv.setTextColor(Color.parseColor("#FA1E25"))
                    binding.mainRvLayout.setBackgroundResource(R.drawable.background_expense)
                }
                else -> {
                    binding.amountTv.text = amount
                    binding.mainRvLayout.setBackgroundResource(R.drawable.background_gray)
                }
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFragmentViewHolder {
        return MainFragmentViewHolder(
            MainRvLayoutBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MainFragmentViewHolder, position: Int) {
        holder.bind(incomeExpenseList[position], position)
    }

    override fun getItemCount(): Int {
        return incomeExpenseList.size
    }

    fun addIncomeExpense(incomeExpense: List<IncomeExpense>){
        this.incomeExpenseList = incomeExpense
        notifyDataSetChanged()
    }

    fun addLimitedIncomeExpense(incomeExpense: List<IncomeExpense>) {
        this.incomeExpenseList = incomeExpense.take(7)
        notifyDataSetChanged()
    }

}
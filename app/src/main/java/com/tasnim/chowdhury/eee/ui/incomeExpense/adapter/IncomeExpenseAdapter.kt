package com.tasnim.chowdhury.eee.ui.incomeExpense.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.databinding.MainRvLayoutBinding
import com.tasnim.chowdhury.eee.data.model.IncomeExpense
import com.tasnim.chowdhury.eee.ui.MainFragmentDirections

class IncomeExpenseAdapter(val context: Context): RecyclerView.Adapter<IncomeExpenseAdapter.IncomeExpenseViewHolder>() {

    private var incomeExpenseList: List<IncomeExpense> = listOf()

    class IncomeExpenseViewHolder(private val binding: MainRvLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(incomeExpense: IncomeExpense, position: Int){
            binding.iconImgV.setImageResource(R.drawable.ic_launcher_background)
            binding.titleTv.text = incomeExpense.iETitle
            binding.typeTv.text = incomeExpense.iEType
            binding.amountTv.text = incomeExpense.iEAmount.toString()

            binding.mainRvLayout.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToIncomeExpenseUpdateFragment(incomeExpense)
                binding.mainRvLayout.findNavController().navigate(action)
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

    fun addIncomeExpense(incomeExpense: List<IncomeExpense>){
        this.incomeExpenseList = incomeExpense
        notifyDataSetChanged()
    }

}
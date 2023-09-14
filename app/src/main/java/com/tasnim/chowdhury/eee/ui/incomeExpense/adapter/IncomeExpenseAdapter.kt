package com.tasnim.chowdhury.eee.ui.incomeExpense.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.data.model.HeaderItem
import com.tasnim.chowdhury.eee.databinding.MainRvLayoutBinding
import com.tasnim.chowdhury.eee.data.model.IncomeExpense
import com.tasnim.chowdhury.eee.databinding.RvHeaderLayoutBinding
import com.tasnim.chowdhury.eee.ui.incomeExpense.details.AllTransactionFragmentDirections

class IncomeExpenseAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var clickCallback:((item:IncomeExpense, flag: String)->Unit)? = null
    var data: MutableList<IncomeExpense> = mutableListOf()

    companion object{
        const val ITEM_TYPE_HEADER = 0
        const val ITEM_TYPE_ITEM = 1
    }

    private var groupedData: List<Any> = listOf()

    inner class HeaderViewHolder(private val binding: RvHeaderLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(date: String){
            binding.headerText.text = date
        }
    }

    inner class IncomeExpenseViewHolder(private val binding: MainRvLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(incomeExpense: IncomeExpense, position: Int){
            val amountPlus = "+${incomeExpense.iEAmount.toString()} ৳"
            val amountMinus = "-${incomeExpense.iEAmount.toString()} ৳"
            val amount = "${incomeExpense.iEAmount.toString()} ৳"
            binding.iconImgV.setImageResource(R.drawable.ic_chart)
            binding.titleTv.text = incomeExpense.iETitle
            binding.dateTv.text = incomeExpense.iEDate
            binding.categoryTv.text = incomeExpense.iECategory

            binding.mainRvLayout.setOnClickListener {
                val action = AllTransactionFragmentDirections.actionAllTransactionFragmentToRecordDetailsFragment(incomeExpense)
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
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            ITEM_TYPE_HEADER -> {
                HeaderViewHolder(
                    RvHeaderLayoutBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }
            ITEM_TYPE_ITEM -> {
                IncomeExpenseViewHolder(
                    MainRvLayoutBinding.inflate(
                        LayoutInflater.from(parent.context), parent, false
                    )
                )
            }
            else -> {
                throw IllegalAccessException("Unknown Viewtype: $viewType")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder){
            is HeaderViewHolder -> {
                val headerItem = groupedData[position] as HeaderItem
                holder.bind(headerItem.date)
            }
            is IncomeExpenseViewHolder -> {
                val incomeExpense = groupedData[position] as IncomeExpense
                holder.bind(incomeExpense, position)
            }
        }
    }

    override fun getItemCount(): Int {
        return groupedData.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (groupedData[position] is HeaderItem){
            ITEM_TYPE_HEADER
        }else{
            ITEM_TYPE_ITEM
        }
    }

    fun setGroupedData(data: List<Any>) {
        groupedData = data
        notifyDataSetChanged()
    }

}
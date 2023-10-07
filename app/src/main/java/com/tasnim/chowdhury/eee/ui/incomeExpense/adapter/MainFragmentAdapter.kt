package com.tasnim.chowdhury.eee.ui.incomeExpense.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.databinding.MainRvLayoutBinding
import com.tasnim.chowdhury.eee.ui.incomeExpense.data.model.IncomeExpense
import com.tasnim.chowdhury.eee.ui.utils.currencyType

class MainFragmentAdapter(val context: Context, flag: String): RecyclerView.Adapter<MainFragmentAdapter.MainFragmentViewHolder>() {

    private var incomeExpenseList: List<IncomeExpense> = listOf()
    var transactionDetails:((item: IncomeExpense)->Unit)? = null

    private val tag = flag

    inner class MainFragmentViewHolder(private val binding: MainRvLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(incomeExpense: IncomeExpense, position: Int){
            val amountPlus = "$currencyType+${incomeExpense.iEAmount.toString()}"
            val amountMinus = "$currencyType-${incomeExpense.iEAmount.toString()}"
            val amount = "$currencyType${incomeExpense.iEAmount.toString()}"
            binding.iconImgV.setImageResource(incomeExpense.categoryIcon)
            binding.iconImgV.background = ContextCompat.getDrawable(context, incomeExpense.catIconBg)
            binding.titleTv.text = incomeExpense.iETitle
            binding.dateTv.text = incomeExpense.iEDate
            binding.categoryTv.text = incomeExpense.iECategory

            binding.mainRvLayout.setOnClickListener {
                when(tag) {
                    "MainFragment" -> {
                        transactionDetails?.invoke(incomeExpense)
                    }
                }
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

            when (incomeExpense.categoryParent) {
                "Food" -> {
                    binding.iconImgV.outlineAmbientShadowColor = ContextCompat.getColor(context, R.color.food_color)
                    binding.iconImgV.outlineSpotShadowColor = ContextCompat.getColor(context, R.color.food_color)
                }
                "Transportation" -> {
                    binding.iconImgV.outlineAmbientShadowColor = ContextCompat.getColor(context, R.color.transportation_color)
                    binding.iconImgV.outlineSpotShadowColor = ContextCompat.getColor(context, R.color.transportation_color)
                }
                "Housing/Rental" -> {
                    binding.iconImgV.outlineAmbientShadowColor = ContextCompat.getColor(context, R.color.housing_color)
                    binding.iconImgV.outlineSpotShadowColor = ContextCompat.getColor(context, R.color.housing_color)
                }
                "Entertainment" -> {
                    binding.iconImgV.outlineAmbientShadowColor = ContextCompat.getColor(context, R.color.entertainment_color)
                    binding.iconImgV.outlineSpotShadowColor = ContextCompat.getColor(context, R.color.entertainment_color)
                }
                "Healthcare/Family" -> {
                    binding.iconImgV.outlineAmbientShadowColor = ContextCompat.getColor(context, R.color.healthcare_color)
                    binding.iconImgV.outlineSpotShadowColor = ContextCompat.getColor(context, R.color.healthcare_color)
                }
                "Shopping" -> {
                    binding.iconImgV.outlineAmbientShadowColor = ContextCompat.getColor(context, R.color.shopping_color)
                    binding.iconImgV.outlineSpotShadowColor = ContextCompat.getColor(context, R.color.shopping_color)
                }
                "Education" -> {
                    binding.iconImgV.outlineAmbientShadowColor = ContextCompat.getColor(context, R.color.education_color)
                    binding.iconImgV.outlineSpotShadowColor = ContextCompat.getColor(context, R.color.education_color)
                }
                "Debt/Tax" -> {
                    binding.iconImgV.outlineAmbientShadowColor = ContextCompat.getColor(context, R.color.debt_color)
                    binding.iconImgV.outlineSpotShadowColor = ContextCompat.getColor(context, R.color.debt_color)
                }
                "Savings" -> {
                    binding.iconImgV.outlineAmbientShadowColor = ContextCompat.getColor(context, R.color.savings_color)
                    binding.iconImgV.outlineSpotShadowColor = ContextCompat.getColor(context, R.color.savings_color)
                }
                "Gifts/Donations" -> {
                    binding.iconImgV.outlineAmbientShadowColor = ContextCompat.getColor(context, R.color.gift_color)
                    binding.iconImgV.outlineSpotShadowColor = ContextCompat.getColor(context, R.color.gift_color)
                }
                "Travel" -> {
                    binding.iconImgV.outlineAmbientShadowColor = ContextCompat.getColor(context, R.color.travel_color)
                    binding.iconImgV.outlineSpotShadowColor = ContextCompat.getColor(context, R.color.travel_color)
                }
                "Others" -> {
                    binding.iconImgV.outlineAmbientShadowColor = ContextCompat.getColor(context, R.color.others_color)
                    binding.iconImgV.outlineSpotShadowColor = ContextCompat.getColor(context, R.color.others_color)
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
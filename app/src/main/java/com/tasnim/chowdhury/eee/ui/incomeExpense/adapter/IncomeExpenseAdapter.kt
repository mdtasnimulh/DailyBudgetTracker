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
import com.tasnim.chowdhury.eee.ui.incomeExpense.data.model.HeaderItem
import com.tasnim.chowdhury.eee.databinding.MainRvLayoutBinding
import com.tasnim.chowdhury.eee.ui.incomeExpense.data.model.IncomeExpense
import com.tasnim.chowdhury.eee.databinding.RvHeaderLayoutBinding
import com.tasnim.chowdhury.eee.ui.incomeExpense.details.AllTransactionFragmentDirections

class IncomeExpenseAdapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var clickCallback:((item: IncomeExpense, flag: String)->Unit)? = null
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
            val amountPlus = "৳ +${incomeExpense.iEAmount.toString()}"
            val amountMinus = "৳ -${incomeExpense.iEAmount.toString()}"
            val amount = "৳ ${incomeExpense.iEAmount.toString()}"

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
                    binding.mainRvLayout.setBackgroundResource(R.drawable.income_bg)
                    binding.mainRvLayout.outlineSpotShadowColor = ContextCompat.getColor(context, R.color.income_color)
                    binding.mainRvLayout.outlineAmbientShadowColor = ContextCompat.getColor(context, R.color.income_color)
                }
                "Expense" -> {
                    binding.amountTv.text = amountMinus
                    binding.amountTv.setTextColor(Color.parseColor("#FA1E25"))
                    binding.mainRvLayout.setBackgroundResource(R.drawable.expense_bg)
                    binding.mainRvLayout.outlineSpotShadowColor = ContextCompat.getColor(context, R.color.expense_color)
                    binding.mainRvLayout.outlineAmbientShadowColor = ContextCompat.getColor(context, R.color.expense_color)
                }
                else -> {
                    binding.amountTv.text = amount
                    binding.mainRvLayout.setBackgroundResource(R.drawable.gray_bg)
                    binding.mainRvLayout.outlineSpotShadowColor = ContextCompat.getColor(context, R.color.graphiteGray)
                    binding.mainRvLayout.outlineAmbientShadowColor = ContextCompat.getColor(context, R.color.graphiteGray)
                }
            }

            when(incomeExpense.iECategory) {
                "Groceries" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_groceries)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_food_bg)
                }
                "Dining Out" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_dining_out)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_food_bg)
                }
                "Snacks" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_snacks)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_food_bg)
                }
                "Beverages" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_beverages)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_food_bg)
                }
                "Bakery" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_bakery)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_food_bg)
                }
                "Desserts" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_deserts)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_food_bg)
                }
                "Fruits" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_fruits)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_food_bg)
                }
                "Vegetables" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_vegetables)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_food_bg)
                }
                "Spices/Seasonings" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_spices)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_food_bg)
                }
                "Meats" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_meats)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_food_bg)
                }
                "Seafood/Fish" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_seafood_fish)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_food_bg)
                }
                "Dairy Products" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_dairy_prsoducts)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_food_bg)
                }
                "Cooking Ingredients" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_cooking_ingredients)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_food_bg)
                }
                "Gas/Fuel" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_gas_fuel)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_transportation_bg)
                }
                "Public Transit" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_public_transit)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_transportation_bg)
                }
                "Vehicle Upgrades" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_vechile_upgrage)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_transportation_bg)
                }
                "Vehicle Maintenance" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_vechile_maintenence)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_transportation_bg)
                }
                "Rent" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_rent)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_housing_bg)
                }
                "Lease/Mortgage" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_lease_mortgase)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_housing_bg)
                }
                "Home Maintenance" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_home_maintenence)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_housing_bg)
                }
                "Utilities" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_utilities)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_housing_bg)
                }
                "Electricity" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_electricity)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_housing_bg)
                }
                "Water" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_water)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_housing_bg)
                }
                "Internet" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_internet)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_housing_bg)
                }
                "Phone" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_phone)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_housing_bg)
                }
                "Movies/Theatre" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_movie_theatre)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_entertainment_bg)
                }
                "Subscriptions" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_subcription)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_entertainment_bg)
                }
                "Hobbies" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_hobbies)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_entertainment_bg)
                }
                "Medical Bills" -> {
                    binding.iconImgV.setImageResource(R.drawable.medical_bills)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_healthcare_bg)
                }
                "Fitness" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_fitness)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_healthcare_bg)
                }
                "Gifts for Family" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_gifts_family)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_healthcare_bg)
                }
                "Hospital Bills" -> {
                    binding.iconImgV.setImageResource(R.drawable.hospital_bills)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_healthcare_bg)
                }
                "Health & Wellness" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_health_wellness)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_healthcare_bg)
                }
                "Shopping" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_shopping)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_shopping_bg)
                }
                "Clothing" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_clothing)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_shopping_bg)
                }
                "Electronics" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_electronics)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_shopping_bg)
                }
                "Tech/Gadgets" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_tech_gadgets)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_shopping_bg)
                }
                "Home Appliances" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_home_applience)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_shopping_bg)
                }
                "Education" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_education)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_education_bg)
                }
                "Courses/Workshops" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_courses_workshop)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_education_bg)
                }
                "Books/Notes" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_books_notes)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_education_bg)
                }
                "Debt" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_debt)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_debt_bg)
                }
                "Loans" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_loan)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_debt_bg)
                }
                "Credit Cards" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_credit_cards)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_debt_bg)
                }
                "Insurance" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_insurance)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_debt_bg)
                }
                "Taxes" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_tax)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_debt_bg)
                }
                "Savings" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_savings)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_savings_bg)
                }
                "Investments" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_investments)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_savings_bg)
                }
                "Vacation Savings" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_vacation_savings)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_savings_bg)
                }
                "Investment Funds" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_investment_funds)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_savings_bg)
                }
                "Emergency Expenses" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_emergency_savings)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_savings_bg)
                }
                "Gifts" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_gifts)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_gift_bg)
                }
                "Charity" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_charity)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_gift_bg)
                }
                "Travel" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_travel)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_travel_bg)
                }
                "Vacation" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_vacation)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_travel_bg)
                }
                "Bus" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_bus)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_travel_bg)
                }
                "Train" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_train)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_travel_bg)
                }
                "Air" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_air)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_travel_bg)
                }
                "Miscellaneous" -> {
                    binding.iconImgV.setImageResource(R.drawable.ic_miscelelinous)
                    binding.iconImgV.background = ContextCompat.getDrawable(context, R.drawable.category_others_bg)
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
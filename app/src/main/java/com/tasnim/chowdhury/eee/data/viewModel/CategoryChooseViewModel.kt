package com.tasnim.chowdhury.eee.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.tasnim.chowdhury.eee.R
import com.tasnim.chowdhury.eee.ui.incomeExpense.ChooseCatModel

class CategoryChooseViewModel(application: Application): AndroidViewModel(application){

    private var _incomeCategory = MutableLiveData<List<ChooseCatModel>>()
    val incomeCategory: LiveData<List<ChooseCatModel>> = _incomeCategory

    private var _expenseCategory = MutableLiveData<List<ChooseCatModel>>()
    val expenseCategory: LiveData<List<ChooseCatModel>> = _expenseCategory

    fun getExpenseCategories(){
        val expenseCategories = listOf<ChooseCatModel>(
            // Food Categories
            ChooseCatModel(1, "Groceries", R.drawable.category_food_bg, "Food", R.drawable.ic_groceries),
            ChooseCatModel(2, "Dining Out", R.drawable.category_food_bg, "Food", R.drawable.ic_dining_out),
            ChooseCatModel(3, "Snacks", R.drawable.category_food_bg, "Food", R.drawable.ic_snacks),
            ChooseCatModel(4, "Beverages", R.drawable.category_food_bg, "Food", R.drawable.ic_beverages),
            ChooseCatModel(5, "Bakery", R.drawable.category_food_bg, "Food", R.drawable.ic_bakery),
            ChooseCatModel(6, "Desserts", R.drawable.category_food_bg, "Food", R.drawable.ic_deserts),
            ChooseCatModel(7, "Fruits", R.drawable.category_food_bg, "Food", R.drawable.ic_fruits),
            ChooseCatModel(8, "Vegetables", R.drawable.category_food_bg, "Food", R.drawable.ic_vegetables),
            ChooseCatModel(9, "Spices/Seasonings", R.drawable.category_food_bg, "Food", R.drawable.ic_spices),
            ChooseCatModel(10, "Meats", R.drawable.category_food_bg, "Food", R.drawable.ic_meats),
            ChooseCatModel(11, "Seafood/Fish", R.drawable.category_food_bg, "Food", R.drawable.ic_seafood_fish),
            ChooseCatModel(12, "Dairy Products", R.drawable.category_food_bg, "Food", R.drawable.ic_dairy_prsoducts),
            ChooseCatModel(13, "Cooking Ingredients", R.drawable.category_food_bg, "Food", R.drawable.ic_cooking_ingredients),

            // Transportation Categories
            ChooseCatModel(14, "Gas/Fuel", R.drawable.category_transportation_bg, "Transportation", R.drawable.ic_gas_fuel),
            ChooseCatModel(15, "Public Transit", R.drawable.category_transportation_bg, "Transportation", R.drawable.ic_public_transit),
            ChooseCatModel(16, "Vehicle Upgrades", R.drawable.category_transportation_bg, "Transportation", R.drawable.ic_vechile_upgrage),
            ChooseCatModel(17, "Vehicle Maintenance", R.drawable.category_transportation_bg, "Transportation", R.drawable.ic_vechile_maintenence),

            // Housing/Rental Categories
            ChooseCatModel(18, "Rent", R.drawable.category_housing_bg, "Housing/Rental", R.drawable.ic_rent),
            ChooseCatModel(19, "Lease/Mortgage", R.drawable.category_housing_bg, "Housing/Rental", R.drawable.ic_lease_mortgase),
            ChooseCatModel(20, "Home Maintenance", R.drawable.category_housing_bg, "Housing/Rental", R.drawable.ic_home_maintenence),
            ChooseCatModel(21, "Utilities", R.drawable.category_housing_bg, "Housing/Rental", R.drawable.ic_utilities),
            ChooseCatModel(22, "Electricity", R.drawable.category_housing_bg, "Housing/Rental", R.drawable.ic_electricity),
            ChooseCatModel(23, "Water", R.drawable.category_housing_bg, "Housing/Rental", R.drawable.ic_water),
            ChooseCatModel(24, "Internet", R.drawable.category_housing_bg, "Housing/Rental", R.drawable.ic_internet),
            ChooseCatModel(25, "Phone", R.drawable.category_housing_bg, "Housing/Rental", R.drawable.ic_phone),

            // Entertainment Categories
            ChooseCatModel(26, "Movies/Theatre", R.drawable.category_entertainment_bg, "Entertainment", R.drawable.ic_movie_theatre),
            ChooseCatModel(27, "Subscriptions", R.drawable.category_entertainment_bg, "Entertainment", R.drawable.ic_subcription),
            ChooseCatModel(28, "Hobbies", R.drawable.category_entertainment_bg, "Entertainment", R.drawable.ic_hobbies),

            // Healthcare/Family Categories
            ChooseCatModel(29, "Medical Bills", R.drawable.category_healthcare_bg, "Healthcare/Family", R.drawable.medical_bills),
            ChooseCatModel(30, "Fitness", R.drawable.category_healthcare_bg, "Healthcare/Family", R.drawable.ic_fitness),
            ChooseCatModel(31, "Gifts for Family", R.drawable.category_healthcare_bg, "Healthcare/Family", R.drawable.ic_gifts_family),
            ChooseCatModel(32, "Hospital Bills", R.drawable.category_healthcare_bg, "Healthcare/Family", R.drawable.hospital_bills),
            ChooseCatModel(33, "Health & Wellness", R.drawable.category_healthcare_bg, "Healthcare/Family", R.drawable.ic_health_wellness),

            // Shopping Categories
            ChooseCatModel(34, "Shopping", R.drawable.category_shopping_bg, "Shopping", R.drawable.ic_shopping),
            ChooseCatModel(35, "Clothing", R.drawable.category_shopping_bg, "Shopping", R.drawable.ic_clothing),
            ChooseCatModel(36, "Electronics", R.drawable.category_shopping_bg, "Shopping", R.drawable.ic_electronics),
            ChooseCatModel(37, "Tech/Gadgets", R.drawable.category_shopping_bg, "Shopping", R.drawable.ic_tech_gadgets),
            ChooseCatModel(38, "Home Appliances", R.drawable.category_shopping_bg, "Shopping", R.drawable.ic_home_applience),

            // Education Categories
            ChooseCatModel(39, "Education", R.drawable.category_education_bg, "Education", R.drawable.ic_education),
            ChooseCatModel(40, "Courses/Workshops", R.drawable.category_education_bg, "Education", R.drawable.ic_courses_workshop),
            ChooseCatModel(41, "Books/Notes", R.drawable.category_education_bg, "Education", R.drawable.ic_books_notes),

            // Debt/Tax Categories
            ChooseCatModel(42, "Debt", R.drawable.category_debt_bg, "Debt/Tax", R.drawable.ic_debt),
            ChooseCatModel(43, "Loans", R.drawable.category_debt_bg, "Debt/Tax", R.drawable.ic_loan),
            ChooseCatModel(44, "Credit Cards", R.drawable.category_debt_bg, "Debt/Tax", R.drawable.ic_credit_cards),
            ChooseCatModel(45, "Insurance", R.drawable.category_debt_bg, "Debt/Tax", R.drawable.ic_insurance),
            ChooseCatModel(46, "Taxes", R.drawable.category_debt_bg, "Debt/Tax", R.drawable.ic_tax),

            // Savings Categories
            ChooseCatModel(47, "Savings", R.drawable.category_savings_bg, "Savings", R.drawable.ic_savings),
            ChooseCatModel(48, "Investments", R.drawable.category_savings_bg, "Savings", R.drawable.ic_investments),
            ChooseCatModel(49, "Vacation Savings", R.drawable.category_savings_bg, "Savings", R.drawable.ic_vacation_savings),
            ChooseCatModel(50, "Investment Funds", R.drawable.category_savings_bg, "Savings", R.drawable.ic_investment_funds),
            ChooseCatModel(51, "Emergency Expenses", R.drawable.category_savings_bg, "Savings", R.drawable.ic_emergency_savings),

            // Gifts/Donations Categories
            ChooseCatModel(52, "Gifts", R.drawable.category_gift_bg, "Gifts/Donations", R.drawable.ic_gifts),
            ChooseCatModel(53, "Charity", R.drawable.category_gift_bg, "Gifts/Donations", R.drawable.ic_charity),

            // Travel Categories
            ChooseCatModel(54, "Travel", R.drawable.category_travel_bg, "Travel", R.drawable.ic_travel),
            ChooseCatModel(55, "Vacation", R.drawable.category_travel_bg, "Travel", R.drawable.ic_vacation),
            ChooseCatModel(56, "Bus", R.drawable.category_travel_bg, "Travel", R.drawable.ic_bus),
            ChooseCatModel(57, "Train", R.drawable.category_travel_bg, "Travel", R.drawable.ic_train),
            ChooseCatModel(58, "Air", R.drawable.category_travel_bg, "Travel", R.drawable.ic_air),

            // Others Categories
            ChooseCatModel(59, "Miscellaneous", R.drawable.category_others_bg, "Others", R.drawable.ic_miscelelinous),
        )
        _expenseCategory.value = expenseCategories
    }

}
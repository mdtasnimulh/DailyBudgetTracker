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
            ChooseCatModel(1, "Groceries", "#A5D6A7", "Food", R.drawable.ic_groceries),
            ChooseCatModel(2, "Dining Out", "#FFCC80", "Food", R.drawable.ic_dining_out),
            ChooseCatModel(3, "Snacks", "#FFD700", "Food", R.drawable.ic_snacks),
            ChooseCatModel(4, "Beverages", "#FFA07A", "Food", R.drawable.ic_beverages),
            ChooseCatModel(5, "Bakery", "#FFB6C1", "Food", R.drawable.ic_bakery),
            ChooseCatModel(6, "Desserts", "#FF69B4", "Food", R.drawable.ic_deserts),
            ChooseCatModel(7, "Fruits", "#98FB98", "Food", R.drawable.ic_fruits),
            ChooseCatModel(8, "Vegetables", "#7CFC00", "Food", R.drawable.ic_vegetables),
            ChooseCatModel(9, "Spices/Seasonings", "#FF6347", "Food", R.drawable.ic_spices),
            ChooseCatModel(10, "Meats", "#FF4500", "Food", R.drawable.ic_meats),
            ChooseCatModel(11, "Seafood/Fish", "#1E90FF", "Food", R.drawable.ic_seafood_fish),
            ChooseCatModel(12, "Dairy Products", "#87CEEB", "Food", R.drawable.ic_dairy_prsoducts),
            ChooseCatModel(13, "Cooking Ingredients", "#FFD700", "Food", R.drawable.ic_cooking_ingredients),

            // Transportation Categories
            ChooseCatModel(14, "Gas/Fuel", "#E57373", "Transportation", R.drawable.ic_gas_fuel),
            ChooseCatModel(15, "Public Transit", "#80CBC4", "Transportation", R.drawable.ic_public_transit),
            ChooseCatModel(16, "Vehicle Upgrades", "#E57373", "Transportation", R.drawable.ic_vechile_upgrage),
            ChooseCatModel(17, "Vehicle Maintenance", "#FFAB91", "Transportation", R.drawable.ic_vechile_maintenence),

            // Housing/Rental Categories
            ChooseCatModel(18, "Rent", "#A5D6A7", "Housing/Rental", R.drawable.ic_rent),
            ChooseCatModel(19, "Lease/Mortgage", "#FFAB91", "Housing/Rental", R.drawable.ic_lease_mortgase),
            ChooseCatModel(20, "Home Maintenance", "#B39DDB", "Housing/Rental", R.drawable.ic_home_maintenence),
            ChooseCatModel(21, "Utilities", "#B39DDB", "Housing/Rental", R.drawable.ic_utilities),
            ChooseCatModel(22, "Electricity", "#E57373", "Housing/Rental", R.drawable.ic_electricity),
            ChooseCatModel(23, "Water", "#FFD54F", "Housing/Rental", R.drawable.ic_water),
            ChooseCatModel(24, "Internet", "#90CAF9", "Housing/Rental", R.drawable.ic_internet),
            ChooseCatModel(25, "Phone", "#FFAB91", "Housing/Rental", R.drawable.ic_phone),

            // Entertainment Categories
            ChooseCatModel(26, "Movies/Theatre", "#FFAB91", "Entertainment", R.drawable.ic_movie_theatre),
            ChooseCatModel(27, "Subscriptions", "#90CAF9", "Entertainment", R.drawable.ic_subcription),
            ChooseCatModel(28, "Hobbies", "#EF9A9A", "Entertainment", R.drawable.ic_hobbies),

            // Healthcare/Family Categories
            ChooseCatModel(29, "Medical Bills", "#B2DFDB", "Healthcare/Family", R.drawable.medical_bills),
            ChooseCatModel(30, "Fitness", "#FFCC80", "Healthcare/Family", R.drawable.ic_fitness),
            ChooseCatModel(31, "Gifts for Family", "#80CBC4", "Healthcare/Family", R.drawable.ic_gifts_family),
            ChooseCatModel(32, "Hospital Bills", "#FFD54F", "Healthcare/Family", R.drawable.hospital_bills),
            ChooseCatModel(33, "Health & Wellness", "#EF9A9A", "Healthcare/Family", R.drawable.ic_health_wellness),

            // Shopping Categories
            ChooseCatModel(34, "Shopping", "#A5D6A7", "Shopping", R.drawable.ic_shopping),
            ChooseCatModel(35, "Clothing", "#B39DDB", "Shopping", R.drawable.ic_clothing),
            ChooseCatModel(36, "Electronics", "#E57373", "Shopping", R.drawable.ic_electronics),
            ChooseCatModel(37, "Tech/Gadgets", "#A5D6A7", "Shopping", R.drawable.ic_tech_gadgets),
            ChooseCatModel(38, "Home Appliances", "#B39DDB", "Shopping", R.drawable.ic_home_applience),

            // Education Categories
            ChooseCatModel(39, "Education", "#90CAF9", "Education", R.drawable.ic_education),
            ChooseCatModel(40, "Courses/Workshops", "#B2DFDB", "Education", R.drawable.ic_courses_workshop),
            ChooseCatModel(41, "Books/Notes", "#A5D6A7", "Education", R.drawable.ic_books_notes),

            // Debt/Tax Categories
            ChooseCatModel(42, "Debt", "#FFD54F", "Debt/Tax", R.drawable.ic_debt),
            ChooseCatModel(43, "Loans", "#9FA8DA", "Debt/Tax", R.drawable.ic_loan),
            ChooseCatModel(44, "Credit Cards", "#FFCC80", "Debt/Tax", R.drawable.ic_credit_cards),
            ChooseCatModel(45, "Insurance", "#A5D6A7", "Debt/Tax", R.drawable.ic_insurance),
            ChooseCatModel(46, "Taxes", "#9FA8DA", "Debt/Tax", R.drawable.ic_tax),

            // Savings Categories
            ChooseCatModel(47, "Savings", "#80DEEA", "Savings", R.drawable.ic_savings),
            ChooseCatModel(48, "Investments", "#E57373", "Savings", R.drawable.ic_investments),
            ChooseCatModel(49, "Vacation Savings", "#80DEEA", "Savings", R.drawable.ic_vacation_savings),
            ChooseCatModel(50, "Investment Funds", "#80CBC4", "Savings", R.drawable.ic_investment_funds),
            ChooseCatModel(51, "Emergency Expenses", "#FFD54F", "Savings", R.drawable.ic_emergency_savings),

            // Gifts/Donations Categories
            ChooseCatModel(52, "Gifts", "#FFD54F", "Gifts/Donations", R.drawable.ic_gifts),
            ChooseCatModel(53, "Charity", "#9FA8DA", "Gifts/Donations", R.drawable.ic_charity),

            // Travel Categories
            ChooseCatModel(54, "Travel", "#80CBC4", "Travel", R.drawable.ic_travel),
            ChooseCatModel(55, "Vacation", "#FFAB91", "Travel", R.drawable.ic_vacation),
            ChooseCatModel(56, "Bus", "#FFAB91", "Travel", R.drawable.ic_bus),
            ChooseCatModel(57, "Train", "#FFAB91", "Travel", R.drawable.ic_train),
            ChooseCatModel(58, "Air", "#FFAB91", "Travel", R.drawable.ic_air),

            // Others Categories
            ChooseCatModel(59, "Miscellaneous", "#E0E0E0", "Others", R.drawable.ic_miscelelinous),
        )
        _expenseCategory.value = expenseCategories
    }

}
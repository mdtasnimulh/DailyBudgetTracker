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
            ChooseCatModel(1, "Groceries", "#A5D6A7", "Food", R.drawable.ic_budget),
            ChooseCatModel(2, "Dining Out", "#FFCC80", "Food", R.drawable.ic_budget),
            ChooseCatModel(3, "Snacks", "#FFD700", "Food", R.drawable.ic_budget),
            ChooseCatModel(4, "Beverages", "#FFA07A", "Food", R.drawable.ic_budget),
            ChooseCatModel(5, "Bakery", "#FFB6C1", "Food", R.drawable.ic_budget),
            ChooseCatModel(6, "Desserts", "#FF69B4", "Food", R.drawable.ic_budget),
            ChooseCatModel(7, "Fruits", "#98FB98", "Food", R.drawable.ic_budget),
            ChooseCatModel(8, "Vegetables", "#7CFC00", "Food", R.drawable.ic_budget),
            ChooseCatModel(9, "Spices/Seasonings", "#FF6347", "Food", R.drawable.ic_budget),
            ChooseCatModel(10, "Meats", "#FF4500", "Food", R.drawable.ic_budget),
            ChooseCatModel(11, "Seafood/Fish", "#1E90FF", "Food", R.drawable.ic_budget),
            ChooseCatModel(12, "Dairy Products", "#87CEEB", "Food", R.drawable.ic_budget),
            ChooseCatModel(13, "Cooking Ingredients", "#FFD700", "Food", R.drawable.ic_budget),
            ChooseCatModel(14, "Eating Out", "#9FA8DA", "Food", R.drawable.ic_budget),

            // Transportation Categories
            ChooseCatModel(15, "Gas/Fuel", "#E57373", "Transportation", R.drawable.ic_budget),
            ChooseCatModel(16, "Public Transit", "#80CBC4", "Transportation", R.drawable.ic_budget),
            ChooseCatModel(17, "Vehicle Upgrades", "#E57373", "Transportation", R.drawable.ic_budget),
            ChooseCatModel(18, "Vehicle Maintenance", "#FFAB91", "Transportation", R.drawable.ic_budget),

            // Housing/Rental Categories
            ChooseCatModel(19, "Rent", "#A5D6A7", "Housing/Rental", R.drawable.ic_budget),
            ChooseCatModel(20, "Lease/Mortgage", "#FFAB91", "Housing/Rental", R.drawable.ic_budget),
            ChooseCatModel(21, "Home Maintenance", "#B39DDB", "Housing/Rental", R.drawable.ic_budget),
            ChooseCatModel(22, "Utilities", "#B39DDB", "Housing/Rental", R.drawable.ic_budget),
            ChooseCatModel(23, "Electricity", "#E57373", "Housing/Rental", R.drawable.ic_budget),
            ChooseCatModel(24, "Water", "#FFD54F", "Housing/Rental", R.drawable.ic_budget),
            ChooseCatModel(25, "Internet", "#90CAF9", "Housing/Rental", R.drawable.ic_budget),
            ChooseCatModel(26, "Phone", "#FFAB91", "Housing/Rental", R.drawable.ic_budget),

            // Entertainment Categories
            ChooseCatModel(27, "Movies/Theatre", "#FFAB91", "Entertainment", R.drawable.ic_budget),
            ChooseCatModel(28, "Subscriptions", "#90CAF9", "Entertainment", R.drawable.ic_budget),
            ChooseCatModel(29, "Hobbies", "#EF9A9A", "Entertainment", R.drawable.ic_budget),

            // Healthcare/Family Categories
            ChooseCatModel(30, "Medical Bills", "#B2DFDB", "Healthcare/Family", R.drawable.ic_budget),
            ChooseCatModel(31, "Fitness", "#FFCC80", "Healthcare/Family", R.drawable.ic_budget),
            ChooseCatModel(32, "Gifts for Family", "#80CBC4", "Healthcare/Family", R.drawable.ic_budget),
            ChooseCatModel(33, "Hospital Bills", "#FFD54F", "Healthcare/Family", R.drawable.ic_budget),
            ChooseCatModel(34, "Health & Wellness", "#EF9A9A", "Healthcare/Family", R.drawable.ic_budget),

            // Shopping Categories
            ChooseCatModel(35, "Shopping", "#A5D6A7", "Shopping", R.drawable.ic_budget),
            ChooseCatModel(36, "Clothing", "#B39DDB", "Shopping", R.drawable.ic_budget),
            ChooseCatModel(37, "Electronics", "#E57373", "Shopping", R.drawable.ic_budget),
            ChooseCatModel(38, "Tech/Gadgets", "#A5D6A7", "Shopping", R.drawable.ic_budget),
            ChooseCatModel(39, "Home Appliances", "#B39DDB", "Shopping", R.drawable.ic_budget),

            // Education Categories
            ChooseCatModel(40, "Education", "#90CAF9", "Education", R.drawable.ic_budget),
            ChooseCatModel(41, "Courses/Workshops", "#B2DFDB", "Education", R.drawable.ic_budget),
            ChooseCatModel(42, "Books/Notes", "#A5D6A7", "Education", R.drawable.ic_budget),

            // Debt/Tax Categories
            ChooseCatModel(43, "Debt", "#FFD54F", "Debt/Tax", R.drawable.ic_budget),
            ChooseCatModel(44, "Loans", "#9FA8DA", "Debt/Tax", R.drawable.ic_budget),
            ChooseCatModel(45, "Credit Cards", "#FFCC80", "Debt/Tax", R.drawable.ic_budget),
            ChooseCatModel(46, "Insurance", "#A5D6A7", "Debt/Tax", R.drawable.ic_budget),
            ChooseCatModel(47, "Taxes", "#9FA8DA", "Debt/Tax", R.drawable.ic_budget),

            // Savings Categories
            ChooseCatModel(48, "Savings", "#80DEEA", "Savings", R.drawable.ic_budget),
            ChooseCatModel(49, "Investments", "#E57373", "Savings", R.drawable.ic_budget),
            ChooseCatModel(50, "Vacation Savings", "#80DEEA", "Savings", R.drawable.ic_budget),
            ChooseCatModel(51, "Investment Funds", "#80CBC4", "Savings", R.drawable.ic_budget),
            ChooseCatModel(52, "Emergency Expenses", "#FFD54F", "Savings", R.drawable.ic_budget),
            ChooseCatModel(53, "Hobbies", "#FFD54F", "Savings", R.drawable.ic_budget),

            // Gifts/Donations Categories
            ChooseCatModel(54, "Gifts", "#FFD54F", "Gifts/Donations", R.drawable.ic_budget),
            ChooseCatModel(55, "Charity", "#9FA8DA", "Gifts/Donations", R.drawable.ic_budget),

            // Travel Categories
            ChooseCatModel(56, "Travel", "#80CBC4", "Travel", R.drawable.ic_budget),
            ChooseCatModel(57, "Vacation", "#FFAB91", "Travel", R.drawable.ic_budget),
            ChooseCatModel(58, "Bus", "#FFAB91", "Travel", R.drawable.ic_budget),
            ChooseCatModel(59, "Train", "#FFAB91", "Travel", R.drawable.ic_budget),
            ChooseCatModel(60, "Air", "#FFAB91", "Travel", R.drawable.ic_budget),

            // Others Categories
            ChooseCatModel(61, "Miscellaneous", "#E0E0E0", "Others", R.drawable.ic_budget),
        )
        _expenseCategory.value = expenseCategories
    }

}
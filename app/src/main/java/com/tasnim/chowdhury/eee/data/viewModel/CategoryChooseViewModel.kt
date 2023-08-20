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
            ChooseCatModel(1, "Groceries", "#A5D6A7", "Food", R.drawable.ic_budget),
            ChooseCatModel(2, "Dining Out", "#FFCC80", "Food", R.drawable.ic_budget),
            ChooseCatModel(3, "Home Cooking", "#FFF59D", "Food", R.drawable.ic_budget),
            ChooseCatModel(4, "Transportation", "#81D4FA", "Transportation", R.drawable.ic_budget),
            ChooseCatModel(5, "Gas/Fuel", "#E57373", "Transportation", R.drawable.ic_budget),
            ChooseCatModel(6, "Public Transit", "#80CBC4", "Transportation", R.drawable.ic_budget),
            ChooseCatModel(7, "Rent/Mortgage", "#FFAB91", "Housing", R.drawable.ic_budget),
            ChooseCatModel(8, "Utilities", "#FFAB91", "Housing", R.drawable.ic_budget),
            ChooseCatModel(9, "Home Maintenance", "#B39DDB", "Housing", R.drawable.ic_budget),
            ChooseCatModel(10, "Entertainment", "#9FA8DA", "Entertainment", R.drawable.ic_budget),
            ChooseCatModel(11, "Movies/Theatre", "#FFAB91", "Entertainment", R.drawable.ic_budget),
            ChooseCatModel(12, "Subscriptions", "#90CAF9", "Entertainment", R.drawable.ic_budget),
            ChooseCatModel(13, "Healthcare", "#80DEEA", "Healthcare", R.drawable.ic_budget),
            ChooseCatModel(14, "Medical Bills", "#B2DFDB", "Healthcare", R.drawable.ic_budget),
            ChooseCatModel(15, "Fitness", "#FFCC80", "Healthcare", R.drawable.ic_budget),
            ChooseCatModel(16, "Shopping", "#A5D6A7", "Shopping", R.drawable.ic_budget),
            ChooseCatModel(17, "Clothing", "#B39DDB", "Shopping", R.drawable.ic_budget),
            ChooseCatModel(18, "Electronics", "#E57373", "Shopping", R.drawable.ic_budget),
            ChooseCatModel(19, "Gifts/Donations", "#FFD54F", "Gifts", R.drawable.ic_budget),
            ChooseCatModel(20, "Charity", "#9FA8DA", "Gifts", R.drawable.ic_budget),
            ChooseCatModel(21, "Travel", "#80CBC4", "Travel", R.drawable.ic_budget),
            ChooseCatModel(22, "Vacation", "#FFAB91", "Travel", R.drawable.ic_budget),
            ChooseCatModel(23, "Business Travel", "#81D4FA", "Travel", R.drawable.ic_budget),
            ChooseCatModel(24, "Education", "#90CAF9", "Education", R.drawable.ic_budget),
            ChooseCatModel(25, "Courses/Workshops", "#B2DFDB", "Education", R.drawable.ic_budget),
            ChooseCatModel(26, "Books/Reading", "#A5D6A7", "Education", R.drawable.ic_budget),
            ChooseCatModel(27, "Debt Payments", "#FFD54F", "Debt", R.drawable.ic_budget),
            ChooseCatModel(28, "Loans", "#9FA8DA", "Debt", R.drawable.ic_budget),
            ChooseCatModel(29, "Credit Cards", "#FFCC80", "Debt", R.drawable.ic_budget),
            ChooseCatModel(30, "Savings", "#80DEEA", "Savings", R.drawable.ic_budget),
            ChooseCatModel(31, "Emergency Fund", "#FFAB91", "Savings", R.drawable.ic_budget),
            ChooseCatModel(32, "Retirement", "#B39DDB", "Savings", R.drawable.ic_budget),
            ChooseCatModel(33, "Investments", "#E57373", "Savings", R.drawable.ic_budget),
            ChooseCatModel(34, "Gifts for Family", "#80CBC4", "Family", R.drawable.ic_budget),
            ChooseCatModel(35, "Childcare", "#FFD54F", "Family", R.drawable.ic_budget),
            ChooseCatModel(36, "Pets", "#A5D6A7", "Family", R.drawable.ic_budget),
            ChooseCatModel(37, "Taxes", "#9FA8DA", "Taxes", R.drawable.ic_budget),
            ChooseCatModel(38, "Income Tax", "#FFCC80", "Taxes", R.drawable.ic_budget),
            ChooseCatModel(39, "Property Tax", "#80DEEA", "Taxes", R.drawable.ic_budget),
            ChooseCatModel(40, "Utilities", "#B39DDB", "Housing", R.drawable.ic_budget),
            ChooseCatModel(41, "Electricity", "#E57373", "Housing", R.drawable.ic_budget),
            ChooseCatModel(42, "Water", "#FFD54F", "Housing", R.drawable.ic_budget),
            ChooseCatModel(43, "Internet", "#90CAF9", "Housing", R.drawable.ic_budget),
            ChooseCatModel(44, "Phone", "#FFAB91", "Housing", R.drawable.ic_budget),
            ChooseCatModel(45, "Home Improvement", "#81D4FA", "Housing", R.drawable.ic_budget),
            ChooseCatModel(46, "Gardening", "#9FA8DA", "Housing", R.drawable.ic_budget),
            ChooseCatModel(47, "Insurance", "#A5D6A7", "Insurance", R.drawable.ic_budget),
            ChooseCatModel(48, "Health Insurance", "#E57373", "Insurance", R.drawable.ic_budget),
            ChooseCatModel(49, "Car Insurance", "#FFCC80", "Insurance", R.drawable.ic_budget),
            ChooseCatModel(50, "Life Insurance", "#80DEEA", "Insurance", R.drawable.ic_budget),
            ChooseCatModel(51, "Hobbies", "#EF9A9A", "Entertainment", R.drawable.ic_budget),
            ChooseCatModel(52, "Personal Care", "#FFD54F", "Healthcare", R.drawable.ic_budget),
            ChooseCatModel(53, "Tech/Gadgets", "#A5D6A7", "Shopping", R.drawable.ic_budget),
            ChooseCatModel(54, "Home Decor", "#FFCC80", "Shopping", R.drawable.ic_budget),
            ChooseCatModel(55, "Vacation Savings", "#80DEEA", "Savings", R.drawable.ic_budget),
            ChooseCatModel(56, "Education Savings", "#B39DDB", "Savings", R.drawable.ic_budget),
            ChooseCatModel(57, "Family Gifts", "#9FA8DA", "Family", R.drawable.ic_budget),
            ChooseCatModel(58, "Vehicle Maintenance", "#FFAB91", "Transportation", R.drawable.ic_budget),
            ChooseCatModel(59, "Gift Cards", "#81D4FA", "Gifts", R.drawable.ic_budget),
            ChooseCatModel(60, "Home Office", "#E57373", "Work", R.drawable.ic_budget),
            ChooseCatModel(61, "Emergency Expenses", "#FFD54F", "Savings", R.drawable.ic_budget),
            ChooseCatModel(62, "Rent/Lease", "#A5D6A7", "Housing", R.drawable.ic_budget),
            ChooseCatModel(63, "Investment Funds", "#80CBC4", "Savings", R.drawable.ic_budget),
            ChooseCatModel(64, "Health & Wellness", "#EF9A9A", "Healthcare", R.drawable.ic_budget),
            ChooseCatModel(65, "Grocery Savings", "#FFAB91", "Savings", R.drawable.ic_budget),
            ChooseCatModel(66, "Home Appliances", "#B39DDB", "Shopping", R.drawable.ic_budget),
            ChooseCatModel(67, "Eating Out", "#9FA8DA", "Food", R.drawable.ic_budget),
            ChooseCatModel(68, "Utilities Savings", "#81D4FA", "Savings", R.drawable.ic_budget),
            ChooseCatModel(69, "Vehicle Upgrades", "#E57373", "Transportation", R.drawable.ic_budget),
            ChooseCatModel(70, "Charitable Donations", "#FFD54F", "Gifts", R.drawable.ic_budget),
            ChooseCatModel(71, "Home Upgrades", "#80DEEA", "Housing", R.drawable.ic_budget),
            ChooseCatModel(72, "Housing Savings", "#E57373", "Savings", R.drawable.ic_budget),
            ChooseCatModel(73, "Electronics", "#9FA8DA", "Shopping", R.drawable.ic_budget),
            ChooseCatModel(74, "Home Office", "#EF9A9A", "Work", R.drawable.ic_budget),
            ChooseCatModel(75, "Rent/Lease", "#A5D6A7", "Housing", R.drawable.ic_budget),
            ChooseCatModel(76, "Investment Funds", "#80CBC4", "Savings", R.drawable.ic_budget),
            ChooseCatModel(77, "Health & Wellness", "#EF9A9A", "Healthcare", R.drawable.ic_budget),
            ChooseCatModel(78, "Grocery Savings", "#FFAB91", "Savings", R.drawable.ic_budget),
            ChooseCatModel(79, "Home Appliances", "#B39DDB", "Shopping", R.drawable.ic_budget),
            ChooseCatModel(80, "Eating Out", "#9FA8DA", "Food", R.drawable.ic_budget),
            ChooseCatModel(81, "Utilities Savings", "#E57373", "Savings", R.drawable.ic_budget),
            ChooseCatModel(82, "Vehicle Upgrades", "#FFD54F", "Transportation", R.drawable.ic_budget),
            ChooseCatModel(83, "Charitable Donations", "#A5D6A7", "Gifts", R.drawable.ic_budget),
            ChooseCatModel(84, "Home Upgrades", "#80DEEA", "Housing", R.drawable.ic_budget),
            ChooseCatModel(85, "Miscellaneous", "#E0E0E0", "Other", R.drawable.ic_budget),
            ChooseCatModel(86, "Snacks", "#FFD700", "Food", R.drawable.ic_budget),
            ChooseCatModel(87, "Beverages", "#FFA07A", "Food", R.drawable.ic_budget),
            ChooseCatModel(88, "Bakery", "#FFB6C1", "Food", R.drawable.ic_budget),
            ChooseCatModel(89, "Desserts", "#FF69B4", "Food", R.drawable.ic_budget),
            ChooseCatModel(90, "Fruits", "#98FB98", "Food", R.drawable.ic_budget),
            ChooseCatModel(91, "Vegetables", "#7CFC00", "Food", R.drawable.ic_budget),
            ChooseCatModel(92, "Canned Foods", "#20B2AA", "Food", R.drawable.ic_budget),
            ChooseCatModel(93, "Spices/Seasonings", "#FF6347", "Food", R.drawable.ic_budget),
            ChooseCatModel(94, "Grains", "#FF7F50", "Food", R.drawable.ic_budget),
            ChooseCatModel(95, "Meats", "#FF4500", "Food", R.drawable.ic_budget),
            ChooseCatModel(96, "Seafood", "#1E90FF", "Food", R.drawable.ic_budget),
            ChooseCatModel(97, "Dairy Products", "#87CEEB", "Food", R.drawable.ic_budget),
            ChooseCatModel(98, "Cooking Ingredients", "#FFD700", "Food", R.drawable.ic_budget),
            ChooseCatModel(99, "Nutrition Supplements", "#32CD32", "Food", R.drawable.ic_budget),
            ChooseCatModel(100, "Organic Foods", "#7FFF00", "Food", R.drawable.ic_budget),
        )
        _expenseCategory.value = expenseCategories
    }

}
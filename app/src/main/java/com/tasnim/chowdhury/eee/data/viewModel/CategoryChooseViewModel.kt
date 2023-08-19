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
            ChooseCatModel(1, "Food", "#FFFFFF", "Food", R.drawable.ic_budget),
            ChooseCatModel(2, "Transportation", "#AABBCC", "Food", R.drawable.ic_budget),
            ChooseCatModel(3, "Housing", "#FFDDDD", "Internet", R.drawable.ic_budget),
            ChooseCatModel(4, "Utilities", "#E6E6E6", "Food", R.drawable.ic_budget),
            ChooseCatModel(5, "Groceries", "#D1E5A9", "Internet", R.drawable.ic_budget),
            ChooseCatModel(6, "Dining Out", "#FAD02E", "Food", R.drawable.ic_budget),
            ChooseCatModel(7, "Healthcare", "#FF9AA2", "Internet", R.drawable.ic_budget),
            ChooseCatModel(8, "Entertainment", "#C7CEEA", "Internet", R.drawable.ic_budget),
            ChooseCatModel(9, "Shopping", "#F5A623", "Internet", R.drawable.ic_budget),
            ChooseCatModel(10, "Debt Payments", "#FFCB77", "Travel", R.drawable.ic_budget),
            ChooseCatModel(11, "Savings", "#98D7C2", "Travel", R.drawable.ic_budget),
            ChooseCatModel(12, "Investments", "#B2CCFF", "Travel", R.drawable.ic_budget),
            ChooseCatModel(13, "Gifts/Donations", "#FF85A1", "Travel", R.drawable.ic_budget),
            ChooseCatModel(14, "Travel", "#D0E6E3", "Travel", R.drawable.ic_budget),
            ChooseCatModel(15, "Education", "#9DD9D2", "Travel", R.drawable.ic_budget),
            ChooseCatModel(16, "Insurance", "#7F8C8D", "Travel", R.drawable.ic_budget),
            ChooseCatModel(17, "Childcare", "#F3B6C2", "Utilities", R.drawable.ic_budget),
            ChooseCatModel(18, "Pets", "#FFD700", "Utilities", R.drawable.ic_budget),
            ChooseCatModel(19, "Taxes", "#C0C0C0", "Utilities", R.drawable.ic_budget),
            ChooseCatModel(20, "Subscriptions", "#A4DDED", "Utilities", R.drawable.ic_budget),
            ChooseCatModel(21, "Fitness", "#E29D9D", "Utilities", R.drawable.ic_budget),
            ChooseCatModel(22, "Beauty", "#FFB6C1", "Fashion", R.drawable.ic_budget),
            ChooseCatModel(23, "Home Improvement", "#7EC850", "Fashion", R.drawable.ic_budget),
            ChooseCatModel(24, "Charity", "#FF5733", "Fashion", R.drawable.ic_budget),
            ChooseCatModel(25, "Other", "#CCCCCC", "Fashion", R.drawable.ic_budget)
        )
        _expenseCategory.value = expenseCategories
    }

}
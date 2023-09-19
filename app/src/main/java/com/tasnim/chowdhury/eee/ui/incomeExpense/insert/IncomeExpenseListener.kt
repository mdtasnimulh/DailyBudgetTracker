package com.tasnim.chowdhury.eee.ui.incomeExpense.insert

interface IncomeExpenseListener {
    fun onCalculatorResultCalculated(result: String)
    fun onCategoryClicked(categoryTitle: String, categoryParent: String, categoryIcon: Int, catIconBg: Int)
}
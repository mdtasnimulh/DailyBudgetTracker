package com.tasnim.chowdhury.eee.ui.incomeExpense.insert

interface IncomeExpenseListener {
    fun onCalculatorResultCalculated(result: String)
    fun setCategoryForInsertFragment(catTitle: String)
}
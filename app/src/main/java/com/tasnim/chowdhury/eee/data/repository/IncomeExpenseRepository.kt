package com.tasnim.chowdhury.eee.data.repository

import androidx.lifecycle.LiveData
import com.tasnim.chowdhury.eee.data.model.IncomeExpense
import com.tasnim.chowdhury.eee.data.data.IncomeExpenseDao
import com.tasnim.chowdhury.eee.data.model.Budget
import kotlinx.coroutines.flow.Flow

class IncomeExpenseRepository(private val incomeExpenseDao: IncomeExpenseDao) {

    // Income Expense
    suspend fun insert(incomeExpense: IncomeExpense){
        incomeExpenseDao.insertIncomeExpense(incomeExpense)
    }
    suspend fun update(incomeExpense: IncomeExpense){
        incomeExpenseDao.updateIncomeExpense(incomeExpense)
    }
    suspend fun delete(incomeExpense: IncomeExpense){
        incomeExpenseDao.deleteRecords(incomeExpense)
    }
    suspend fun deleteAllRecords(){
        incomeExpenseDao.deleteAllRecords()
    }
    fun searchDatabase(searchQuery: String): Flow<List<IncomeExpense>>{
        return incomeExpenseDao.searchDatabase(searchQuery)
    }
    val getAllIncomeExpense: LiveData<List<IncomeExpense>> = incomeExpenseDao.getAllIncomeExpense()
    val getAllIncome: LiveData<List<IncomeExpense>> = incomeExpenseDao.getAllIncome()
    val getAllExpense: LiveData<List<IncomeExpense>> = incomeExpenseDao.getAllExpense()
    fun getFirstFiveIncomeExpense(): LiveData<List<IncomeExpense>> {
        return incomeExpenseDao.getFirstFiveIncomeExpense()
    }

    // Budget
    suspend fun insertBudget(budget: Budget){
        incomeExpenseDao.insertBudget(budget)
    }
    suspend fun updateBudget(budget: Budget){
        incomeExpenseDao.updateBudget(budget)
    }
    suspend fun deleteBudget(budget: Budget){
        incomeExpenseDao.deleteBudget(budget)
    }
    suspend fun deleteAllBudget(){
        incomeExpenseDao.deleteAllBudget()
    }
    fun searchBudget(searchBudget: String): Flow<List<Budget>>{
        return incomeExpenseDao.searchBudget(searchBudget)
    }
    val getAllBudget: LiveData<List<Budget>> = incomeExpenseDao.getAllBudget()

}
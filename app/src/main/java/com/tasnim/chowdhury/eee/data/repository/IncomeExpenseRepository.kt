package com.tasnim.chowdhury.eee.data.repository

import androidx.lifecycle.LiveData
import com.tasnim.chowdhury.eee.data.model.IncomeExpense
import com.tasnim.chowdhury.eee.data.data.IncomeExpenseDao
import kotlinx.coroutines.flow.Flow

class IncomeExpenseRepository(private val incomeExpenseDao: IncomeExpenseDao) {

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

}
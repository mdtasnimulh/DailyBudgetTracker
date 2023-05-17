package com.tasnim.chowdhury.eee.model.repository

import androidx.lifecycle.LiveData
import com.tasnim.chowdhury.eee.model.data.IncomeExpense
import com.tasnim.chowdhury.eee.model.database.IncomeExpenseDao

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

    val getAllIncomeExpense: LiveData<List<IncomeExpense>> = incomeExpenseDao.getAllIncomeExpense()

    //val getAllIncome: LiveData<List<IncomeExpense>> = incomeExpenseDao.getAllIncome()

    //val getAllExpense: LiveData<List<IncomeExpense>> = incomeExpenseDao.getAllExpense()

}
package com.tasnim.chowdhury.eee

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*

@Dao
interface IncomeExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIncomeExpense(incomeExpense: IncomeExpense)

    @Update
    suspend fun updateIncomeExpense(incomeExpense: IncomeExpense)

    @Delete
    suspend fun deleteRecords(incomeExpense: IncomeExpense)

    @Query("Select * from income_expense_table order by id ASC")
    suspend fun getAllIncomeExpense(): List<IncomeExpense>

    @Query("Select * from income_expense_table where iEType = 'Income'")
    suspend fun getAllIncome(): List<IncomeExpense>

    @Query("Select * from income_expense_table where iEType = 'Expense'")
    suspend fun getAllExpense(): List<IncomeExpense>

}
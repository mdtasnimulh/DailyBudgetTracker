package com.tasnim.chowdhury.eee.model

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tasnim.chowdhury.eee.model.data.IncomeExpense

@Dao
interface IncomeExpenseDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIncomeExpense(incomeExpense: IncomeExpense)

    @Update
    suspend fun updateIncomeExpense(incomeExpense: IncomeExpense)

    @Delete
    suspend fun deleteRecords(incomeExpense: IncomeExpense)

    @Query("Select * from income_expense_table order by id ASC")
    fun getAllIncomeExpense(): LiveData<List<IncomeExpense>>

    @Query("Select * from income_expense_table where iEType = 'Income'")
    fun getAllIncome(): LiveData<List<IncomeExpense>>

    @Query("Select * from income_expense_table where iEType = 'Expense'")
    fun getAllExpense(): LiveData<List<IncomeExpense>>

}
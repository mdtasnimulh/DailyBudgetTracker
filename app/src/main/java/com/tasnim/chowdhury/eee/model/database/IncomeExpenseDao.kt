package com.tasnim.chowdhury.eee.model.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tasnim.chowdhury.eee.model.data.IncomeExpense

@Dao
interface IncomeExpenseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIncomeExpense(incomeExpense: IncomeExpense)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateIncomeExpense(incomeExpense: IncomeExpense)

    @Delete
    suspend fun deleteRecords(incomeExpense: IncomeExpense)

    @Query("SELECT * FROM income_expense_table ORDER BY iEId DESC")
    fun getAllIncomeExpense(): LiveData<List<IncomeExpense>>

    /*@Query("Select * from income_expense_table where iEType = 'Income'")
    fun getAllIncome(): List<IncomeExpense>

    @Query("Select * from income_expense_table where iEType = 'Expense'")
    fun getAllExpense(): List<IncomeExpense>*/

}
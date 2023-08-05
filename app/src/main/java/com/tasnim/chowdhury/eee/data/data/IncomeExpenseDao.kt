package com.tasnim.chowdhury.eee.data.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tasnim.chowdhury.eee.data.model.IncomeExpense
import kotlinx.coroutines.flow.Flow

@Dao
interface IncomeExpenseDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIncomeExpense(incomeExpense: IncomeExpense)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateIncomeExpense(incomeExpense: IncomeExpense)

    @Delete
    suspend fun deleteRecords(incomeExpense: IncomeExpense)

    @Query("DELETE FROM income_expense_table")
    suspend fun deleteAllRecords()

    @Query("SELECT * FROM income_expense_table ORDER BY iEId DESC")
    fun getAllIncomeExpense(): LiveData<List<IncomeExpense>>

    @Query("SELECT * FROM income_expense_table WHERE iETitle LIKE :searchQuery OR iECategory LIKE :searchQuery")
    fun searchDatabase(searchQuery: String): Flow<List<IncomeExpense>>

    @Query("Select * from income_expense_table where iEType = 'Income'")
    fun getAllIncome(): LiveData<List<IncomeExpense>>

    @Query("Select * from income_expense_table where iEType = 'Expense'")
    fun getAllExpense(): LiveData<List<IncomeExpense>>

    @Query("SELECT * FROM income_expense_table ORDER BY iEId DESC LIMIT 5")
    fun getFirstFiveIncomeExpense(): LiveData<List<IncomeExpense>>

}
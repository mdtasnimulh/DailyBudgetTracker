package com.tasnim.chowdhury.eee.data.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tasnim.chowdhury.eee.data.model.Budget
import com.tasnim.chowdhury.eee.data.model.IncomeExpense
import kotlinx.coroutines.flow.Flow

@Dao
interface IncomeExpenseDao {

    // Income Expense
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

    // Budget
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBudget(budget: Budget)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateBudget(budget: Budget)
    @Delete
    suspend fun deleteBudget(budget: Budget)
    @Query("SELECT * FROM budget_table ORDER BY budgetId DESC")
    fun getAllBudget(): LiveData<List<Budget>>
    @Query("DELETE FROM budget_table")
    suspend fun deleteAllBudget()
    @Query("SELECT * FROM budget_table WHERE budgetTitle LIKE :searchBudget OR budgetCategory LIKE :searchBudget")
    fun searchBudget(searchBudget: String): Flow<List<Budget>>

}
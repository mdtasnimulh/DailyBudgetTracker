package com.tasnim.chowdhury.eee.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.tasnim.chowdhury.eee.data.data.IncomeExpenseDatabase
import com.tasnim.chowdhury.eee.data.model.Budget
import com.tasnim.chowdhury.eee.data.model.IncomeExpense
import com.tasnim.chowdhury.eee.data.repository.IncomeExpenseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IncomeExpenseViewModel(application: Application): AndroidViewModel(application) {

    val getAllIncomeExpense: LiveData<List<IncomeExpense>>
    val getAllIncome: LiveData<List<IncomeExpense>>
    val getAllExpense: LiveData<List<IncomeExpense>>
    private val repository: IncomeExpenseRepository

    val getAllBudget: LiveData<List<Budget>>

    val getFirstFiveIncomeExpense: LiveData<List<IncomeExpense>>

    init {
        val incomeExpenseDao = IncomeExpenseDatabase.getDatabase(application).getIncomeExpenseDao()
        repository = IncomeExpenseRepository(incomeExpenseDao)
        getAllIncomeExpense = repository.getAllIncomeExpense
        getAllIncome = repository.getAllIncome
        getAllExpense = repository.getAllExpense
        getFirstFiveIncomeExpense = repository.getFirstFiveIncomeExpense()

        getAllBudget = repository.getAllBudget
    }

    fun insertIncomeExpense(incomeExpense: IncomeExpense){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(incomeExpense)
        }
    }
    fun insertBudget(budget: Budget){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertBudget(budget)
        }
    }

    fun updateIncomeExpense(incomeExpense: IncomeExpense){
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(incomeExpense)
        }
    }
    fun updateBudget(budget: Budget){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateBudget(budget)
        }
    }

    fun deleteIncomeExpense(incomeExpense: IncomeExpense){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(incomeExpense)
        }
    }
    fun deleteBudget(budget: Budget){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteBudget(budget)
        }
    }

    fun deleteAllIncomeExpense(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllRecords()
        }
    }
    fun deleteAllBudget(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllBudget()
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<IncomeExpense>>{
        return repository.searchDatabase(searchQuery).asLiveData()
    }
    fun searchBudget(searchBudget: String): LiveData<List<Budget>>{
        return repository.searchBudget(searchBudget).asLiveData()
    }

}
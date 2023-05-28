package com.tasnim.chowdhury.eee.data.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.tasnim.chowdhury.eee.data.data.IncomeExpenseDatabase
import com.tasnim.chowdhury.eee.data.model.IncomeExpense
import com.tasnim.chowdhury.eee.data.repository.IncomeExpenseRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IncomeExpenseViewModel(application: Application): AndroidViewModel(application) {

    val getAllIncomeExpense: LiveData<List<IncomeExpense>>
    val getAllIncome: LiveData<List<IncomeExpense>>
    val getAllExpense: LiveData<List<IncomeExpense>>
    private val repository: IncomeExpenseRepository

    init {
        val incomeExpenseDao = IncomeExpenseDatabase.getDatabase(application).getIncomeExpenseDao()
        repository = IncomeExpenseRepository(incomeExpenseDao)
        getAllIncomeExpense = repository.getAllIncomeExpense
        getAllIncome = repository.getAllIncome
        getAllExpense = repository.getAllExpense
    }

    fun insertIncomeExpense(incomeExpense: IncomeExpense){
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(incomeExpense)
        }
    }

    fun updateIncomeExpense(incomeExpense: IncomeExpense){
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(incomeExpense)
        }
    }

    fun deleteIncomeExpense(incomeExpense: IncomeExpense){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(incomeExpense)
        }
    }

    fun deleteAllIncomeExpense(){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllRecords()
        }
    }

    fun searchDatabase(searchQuery: String): LiveData<List<IncomeExpense>>{
        return repository.searchDatabase(searchQuery).asLiveData()
    }

}
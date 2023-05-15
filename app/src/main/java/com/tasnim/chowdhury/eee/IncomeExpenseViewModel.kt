package com.tasnim.chowdhury.eee

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tasnim.chowdhury.eee.model.IncomeExpenseDatabase
import com.tasnim.chowdhury.eee.model.data.IncomeExpense
import com.tasnim.chowdhury.eee.model.repository.IncomeExpenseRepository
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

    fun deleteAllIncomeExpense(incomeExpense: IncomeExpense){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(incomeExpense)
        }
    }


}
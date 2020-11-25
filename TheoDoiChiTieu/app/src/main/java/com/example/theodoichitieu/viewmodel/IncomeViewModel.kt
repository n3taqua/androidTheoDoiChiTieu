package com.example.theodoichitieu.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theodoichitieu.model.Income
import com.example.theodoichitieu.repositories.InComeRepository
import com.example.theodoichitieu.util.NEXT_MOTH_BY_NUM
import com.example.theodoichitieu.util.previousMonth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class IncomeViewModel(apllication:Application):AndroidViewModel(apllication) {
    private val incomeRepositories=InComeRepository(apllication)
    fun listIncomeOfDay(date:Long): LiveData<MutableList<Income>> {
        return incomeRepositories.getListIncomeOfDay(date)
    }
    fun sumMoneyOfDay(date:Long): LiveData<Int>  {
        val temp=incomeRepositories.getSumMoneyOfDay(date)
        return temp
    }
    fun averageMoneyOfDay(date: Long): LiveData<Int>  {
        val temp=incomeRepositories.getEverageMoneyOfDay(date)
        return temp
    }
    fun sumMoneyOfMoth(date:Long): LiveData<Int>  {
        val monthStrat= previousMonth(date)
        val monthEnd=date
        val temp=incomeRepositories.getSumMoneyOfRageDay(monthStrat,monthEnd)
        return temp
    }
    fun averageMoneyOfMont(date: Long): LiveData<Int>  {
        val monthStrat= previousMonth(date)
        val monthEnd=date
        val temp=incomeRepositories.getEverageMoneyOfRage(monthStrat,monthEnd)
        return temp
    }
    fun update(income: Income)=viewModelScope.launch (Dispatchers.IO){
        incomeRepositories.updateIncome(income)
    }
    fun insert(income: Income) = viewModelScope.launch(Dispatchers.IO) {
        incomeRepositories.insertIncome(income)
    }
    fun delete(income: Income) =viewModelScope.launch  (Dispatchers.IO){
        incomeRepositories.deleteIncome(income)
    }
}
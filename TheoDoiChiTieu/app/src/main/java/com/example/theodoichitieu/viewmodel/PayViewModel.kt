package com.example.theodoichitieu.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.theodoichitieu.model.Income
import com.example.theodoichitieu.model.Pay
import com.example.theodoichitieu.repositories.InComeRepository
import com.example.theodoichitieu.repositories.PayRepository
import com.example.theodoichitieu.util.NEXT_MOTH_BY_NUM
import com.example.theodoichitieu.util.previousMonth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PayViewModel(apllication: Application): AndroidViewModel(apllication) {
    private val payRepositories= PayRepository(apllication)

    fun listPayOfDay(date:Long): LiveData<MutableList<Pay>> {
        return payRepositories.getListPayOfDay(date)
    }
    fun sumMoneyOfDay(date:Long): LiveData<Int> {
        val temp=payRepositories.getSumAMoneyOfDay(date)
        return temp
    }
    fun averageMoneyOfDay(date: Long):LiveData<Int>{
        val temp=payRepositories.getEverageMoneyOfDay(date)
        return temp
    }
   fun sumMoneyOfMoth(date:Long): LiveData<Int> {
        val monthStrat= previousMonth(date)
        val monthEnd=date
        val temp=payRepositories.getSumMoneyOfRageDay(monthStrat,monthEnd)
        return temp
    }
    fun averageMoneyOfMonth(date: Long): LiveData<Int>{
        val monthStrat=previousMonth(date)
        val monthEnd=date
        val temp=payRepositories.getAverageMoneyOfRage(monthStrat,monthEnd)
        return temp
    }

    fun update(pay: Pay)=viewModelScope.launch (Dispatchers.IO){
        payRepositories.updatePay(pay)
    }
    fun insert(pay: Pay) = viewModelScope.launch(Dispatchers.IO) {
        payRepositories.insertPay(pay)
    }
    fun delete(pay: Pay) =viewModelScope.launch  (Dispatchers.IO){
        payRepositories.deletePay(pay)
    }
}
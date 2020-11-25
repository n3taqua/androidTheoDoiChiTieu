package com.example.theodoichitieu.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.theodoichitieu.data.FinanceTradingDatabase
import com.example.theodoichitieu.model.Income
import com.example.theodoichitieu.model.SumAndEverage
import com.example.theodoichitieu.util.getDatabase

class InComeRepository(application: Application) {
    val incomeDAO= getDatabase(application).incomeDao()
    fun getEverageMoneyOfRage(start:Long,end:Long): LiveData<Int> {
        return incomeDAO.everageOfRangeDay(start,end)
    }
    fun getSumMoneyOfRageDay(start:Long,end:Long): LiveData<Int> {
       return incomeDAO.sumOfRangeDay(start,end)
    }
    fun getEverageMoneyOfDay(date:Long): LiveData<Int> {
       return incomeDAO.everageOfDay(date)
    }
    fun getSumMoneyOfDay(date:Long): LiveData<Int> {
        return incomeDAO.sumOfDay(date)
    }
   /* fun getIncomeById(id:Int){
      return  incomeDAO.findIncomeById(id)
    }*/
    fun getListIncomeOfDay(date:Long): LiveData<MutableList<Income>> {
      return  incomeDAO.findIncomeByDay(date)
    }
    fun insertIncome(income: Income){
        incomeDAO.insertIncome(income)
    }

    fun updateIncome(income: Income){
        incomeDAO.updateIncome(income)
    }
    fun deleteIncome(income: Income){
        incomeDAO.deleteIncome(income)
    }
}
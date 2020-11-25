package com.example.theodoichitieu.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.theodoichitieu.data.FinanceTradingDatabase
import com.example.theodoichitieu.model.Pay
import com.example.theodoichitieu.model.SumAndEverage
import com.example.theodoichitieu.util.getDatabase

class PayRepository(application: Application)  {
    val payDAO= getDatabase(application).payDao()
    fun getAverageMoneyOfRage(start:Long,end:Long):LiveData<Int> {
       return payDAO.everageOfRangeDay(start,end)
    }
    fun getSumMoneyOfRageDay(start:Long,end:Long): LiveData<Int> {
        return payDAO.everageOfRangeDay(start,end)
    }
    fun getEverageMoneyOfDay(date:Long): LiveData<Int> {
        return payDAO.everageOfDay(date)
    }
    fun getSumAMoneyOfDay(date:Long): LiveData<Int> {
        print("$payDAO.sumOfDay(date) thuwrjajaja â a a a â a a ")
        return payDAO.sumOfDay(date)
    }
    fun getListPayOfDay(date:Long): LiveData<MutableList<Pay>> {
        return payDAO.findPayByDay(date)
    }
    /*fun getPayById(id:Int){
        return payDAO.findPayById(id)
    }*/
    fun insertPay(pay: Pay){
        payDAO.insertPay(pay)
    }

    fun updatePay(pay: Pay){
        payDAO.updatePay(pay)
    }
    fun deletePay(pay: Pay){
        payDAO.deletePay(pay)
    }
}
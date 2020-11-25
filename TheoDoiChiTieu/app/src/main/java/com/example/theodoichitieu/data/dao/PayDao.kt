package com.example.theodoichitieu.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.theodoichitieu.model.Pay
import com.example.theodoichitieu.model.SumAndEverage
import java.util.*

@Dao
interface PayDao {
    @Query("Select  SUM(money) from pay_table where date BETWEEN :startDate AND :endDate")
    fun sumOfRangeDay(startDate:Long,endDate:Long): LiveData<Int>
    @Query("Select AVG(money) from pay_table where date BETWEEN :startDate AND :endDate")
    fun everageOfRangeDay(startDate:Long,endDate:Long): LiveData<Int>
    @Query("Select AVG(money)  from pay_table where date=:dateSeclected")
    fun everageOfDay(dateSeclected: Long): LiveData<Int>
    @Query("Select SUM(money) as value from pay_table where date=:dateSeclected")
    fun sumOfDay(dateSeclected: Long): LiveData<Int>
   // @Query("Select * from pay_table where id=:id")
   //fun findPayById(id:Int)
    @Query("Select * from pay_table where date=:dateSelected")
    fun findPayByDay(dateSelected:Long):LiveData<MutableList<Pay>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPay(pay: Pay)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updatePay(pay: Pay)
    @Delete()
    fun deletePay(pay: Pay)
}
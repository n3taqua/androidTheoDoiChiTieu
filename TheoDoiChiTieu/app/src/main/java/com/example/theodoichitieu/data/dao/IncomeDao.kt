package com.example.theodoichitieu.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.theodoichitieu.model.Income
import com.example.theodoichitieu.model.SumAndEverage

@Dao
interface IncomeDao {
    @Query("Select  AVG(money)  from income_table where date BETWEEN :startDate AND :endDate")
    fun everageOfRangeDay(startDate:Long,endDate:Long):LiveData<Int>
    @Query("Select  SUM(money)from income_table where date BETWEEN :startDate AND :endDate")
    fun sumOfRangeDay(startDate:Long,endDate:Long):LiveData<Int>
    @Query("Select AVG(money) from income_table where date=:dateSeclected")
    fun everageOfDay(dateSeclected: Long):LiveData<Int>
    @Query("Select SUM(money)  from income_table where date=:dateSeclected")
    fun sumOfDay(dateSeclected: Long):LiveData<Int>
   // @Query("Select * from income_table where id=:id")
   // fun findIncomeById(id:Int)
    @Query("select *from income_table where date=:dateSeclected")
    fun findIncomeByDay(dateSeclected:Long):LiveData<MutableList<Income>>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertIncome(income: Income)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateIncome(income: Income)
    @Delete()
    fun deleteIncome(income: Income)
}
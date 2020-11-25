package com.example.theodoichitieu.data.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.theodoichitieu.model.Catogories
@Dao
interface CatogoriesDao {
    @Query("select * from catogories_table")
    fun allCatogories():LiveData<MutableList<Catogories>>
    @Query("select * from catogories_table where id=:id")
    fun findCatogoryById(id:Int):Catogories
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCatogories(catogories: Catogories)
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateCatogories(catogories: Catogories)
    @Delete()
    fun deleteCatogories(catogories: Catogories)
}
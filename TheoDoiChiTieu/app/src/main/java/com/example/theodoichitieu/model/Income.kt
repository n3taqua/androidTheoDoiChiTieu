package com.example.theodoichitieu.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.theodoichitieu.util.DateConVerter
import java.util.*

@Entity(tableName = "income_table")
data class Income (
    var title:String,
    var money:Int,
    var date: Long,
    //var day:Int,
    //var month:Int,
    //var year:Int,
    var time:Long,
    var catogoryId:Int=0
){
    @PrimaryKey(autoGenerate = true)
    var id:Int=0
}
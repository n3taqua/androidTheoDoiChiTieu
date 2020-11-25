package com.example.theodoichitieu.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "catogories_table")
data class Catogories(
    var title:String,
    var detail:String
){
    @PrimaryKey(autoGenerate = true)
    var id:Int=0
}
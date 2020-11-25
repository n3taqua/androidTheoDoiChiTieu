package com.example.theodoichitieu.util

import android.app.Application
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.example.theodoichitieu.data.FinanceTradingDatabase
import java.util.*

val MONEY_EXCHANGE_LINK="https:// www.dongabank.com.vn/"
val DB_NAME="finance_trading_db"
val NEXT_MOTH_BY_NUM=10000
val DAY_NUM_SPACING=1000000
val FULL_YEAR_CONSTANT=1900
val MILION=1000000000
fun yearToLongYear(currentDay:Calendar):Long{
    return (currentDay.get(Calendar.DAY_OF_MONTH)*1000000+ fixMonth(currentDay.get(Calendar.MONTH))*10000+currentDay.get(Calendar.YEAR)).toLong()
}
fun intoTwoCharacter(string:String):String{
    if (string.length==1){
        return "0$string"
    }
    return string
}
fun fixMonth(month:Int):Int{
    return month+1
}
fun getDatabase(application: Application): FinanceTradingDatabase {
    return FinanceTradingDatabase.getDatabase(application)
}

fun getRandomColor(): Int {
    val generator = ColorGenerator.MATERIAL
    return generator.randomColor
}
fun previousMonth(date:Long):Long{
    var value=date
    val temp=date/10000
    val month=(temp%100).toInt()
    if(month == 0){
        value=date-1+120000
    }
    else{
        value=date- NEXT_MOTH_BY_NUM
    }
    return value
}
fun previousDay(date:Long):Long{
    var value=date
    val day=date/1000000.toInt()
    val temp=date/10000.toInt()
    val month:Int= (temp%100).toInt()
    if(day.equals(0)){
        when(month){
            1,3,5,7,8,10,12->{
                value=date-10000+31000000
            }
            4,6,9,11->{
                value=date-10000+30000000
            }
            2->{
                if(isLapYear(date)){
                    value=date-10000+29000000
                }
                else{
                    value=date-10000+28000000
                }
            }
        }

        }
    else
    {
        value -= 1000000
    }
    return value
}

fun isLapYear(date: Long): Boolean {
    val year=(date%10000).toInt()
    if((year/4) == 0 && year/400 != 0)
        return true
    return false
}

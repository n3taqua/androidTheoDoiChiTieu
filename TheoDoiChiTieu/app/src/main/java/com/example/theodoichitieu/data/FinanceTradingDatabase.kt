package com.example.theodoichitieu.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.theodoichitieu.data.dao.CatogoriesDao
import com.example.theodoichitieu.data.dao.IncomeDao
import com.example.theodoichitieu.data.dao.PayDao
import com.example.theodoichitieu.model.Catogories
import com.example.theodoichitieu.model.Income
import com.example.theodoichitieu.model.Pay
import com.example.theodoichitieu.util.DB_NAME

@Database(entities = [Income::class,Pay::class,Catogories::class], version = 5)
abstract  class FinanceTradingDatabase : RoomDatabase() {
    abstract fun payDao():PayDao
    abstract fun incomeDao():IncomeDao
    abstract fun catogoriesDao():CatogoriesDao
    companion object {
        @Volatile
        private var INSTANCE: FinanceTradingDatabase? = null
        fun getDatabase(context: Context): FinanceTradingDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FinanceTradingDatabase::class.java,
                    DB_NAME
                ) .fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
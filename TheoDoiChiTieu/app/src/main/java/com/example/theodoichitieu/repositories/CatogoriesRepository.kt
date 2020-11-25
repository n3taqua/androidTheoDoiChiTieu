package com.example.theodoichitieu.repositories

import android.app.Application
import androidx.lifecycle.LiveData
import com.example.theodoichitieu.model.Catogories

import com.example.theodoichitieu.util.getDatabase

class CatogoriesRepository(application: Application) {
    val catogoriesDao= getDatabase(application).catogoriesDao()
    fun findCatogoriesById(id:Int): Catogories {
        return catogoriesDao.findCatogoryById(id)
    }
    fun getAllCatogories(): LiveData<MutableList<Catogories>> {
        return catogoriesDao.allCatogories()
    }
    fun insertcatogories(catogories: Catogories){
        catogoriesDao.insertCatogories(catogories)
    }

    fun updatecatogories(catogories: Catogories){
        catogoriesDao.updateCatogories(catogories)
    }
    fun deletecatogories(catogories: Catogories){
        catogoriesDao.deleteCatogories(catogories)
    }
}
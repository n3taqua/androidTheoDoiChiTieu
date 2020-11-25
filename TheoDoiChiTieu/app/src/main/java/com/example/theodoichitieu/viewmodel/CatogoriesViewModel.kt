package com.example.theodoichitieu.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.theodoichitieu.model.Catogories
import com.example.theodoichitieu.model.Income
import com.example.theodoichitieu.repositories.CatogoriesRepository
import com.example.theodoichitieu.repositories.InComeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CatogoriesViewModel(apllication: Application): AndroidViewModel(apllication) {
    private val categoriesRepositories= CatogoriesRepository(apllication)

    fun getCatogoriesById(id:Int): Catogories {
        return categoriesRepositories.findCatogoriesById(id)
    }
    fun getAllCatogories(): LiveData<MutableList<Catogories>> {
        return categoriesRepositories.getAllCatogories()
    }

    fun update(catogories: Catogories)=viewModelScope.launch (Dispatchers.IO){
        categoriesRepositories.updatecatogories(catogories)
    }
    fun insert(catogories: Catogories) = viewModelScope.launch(Dispatchers.IO) {
        categoriesRepositories.insertcatogories(catogories)
    }
    fun delete(catogories: Catogories) =viewModelScope.launch  (Dispatchers.IO){
        categoriesRepositories.deletecatogories(catogories)
    }
}
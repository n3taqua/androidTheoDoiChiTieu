package com.example.theodoichitieu.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.theodoichitieu.model.MoneyExchange
import com.example.theodoichitieu.repositories.MoneyExchangeRepository

class MoneyExchangeViewModel(application: Application): AndroidViewModel(application) {
    val moneyExchangeRepository=MoneyExchangeRepository()
    val listMoneyExchange: MutableLiveData<MutableList<MoneyExchange>>
    init {
        listMoneyExchange=moneyExchangeRepository.getMoneyExchangeList()
    }
}
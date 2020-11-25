package com.example.theodoichitieu.repositories

import androidx.lifecycle.MutableLiveData
import com.example.theodoichitieu.data.api.MoneySevice
import com.example.theodoichitieu.model.Exchange
import com.example.theodoichitieu.model.MoneyExchange
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoneyExchangeRepository {
    val moneyExchangeApi=MoneySevice.getInstance()
    fun getMoneyExchangeList():MutableLiveData<MutableList<MoneyExchange>>{
        var listExchange= MutableLiveData<MutableList<MoneyExchange>>()
        moneyExchangeApi.getApi().getMoneyExchangeList().enqueue(object :Callback<Exchange>{
            override fun onFailure(call: Call<Exchange>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<Exchange>?, response: Response<Exchange>?) {
                response?.let {  listExchange.value=response.body().items }

            }

        })
        return listExchange
    }
}
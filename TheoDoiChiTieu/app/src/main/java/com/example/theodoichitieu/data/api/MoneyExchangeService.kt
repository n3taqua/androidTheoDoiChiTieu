package com.example.theodoichitieu.data.api

import com.example.theodoichitieu.model.Exchange
import com.example.theodoichitieu.model.MoneyExchange
import retrofit2.Call
import retrofit2.http.GET

interface MoneyExchangeService {
    @GET("exchange/export")
    fun getMoneyExchangeList(): Call<Exchange>
}
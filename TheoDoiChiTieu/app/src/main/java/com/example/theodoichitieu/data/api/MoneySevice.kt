package com.example.theodoichitieu.data.api

import com.example.theodoichitieu.util.MONEY_EXCHANGE_LINK
import okhttp3.ConnectionSpec
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class MoneySevice {
    private var api: MoneyExchangeService
    init {
        api = createInstance()
    }

    companion object{
        private var mInstance : MoneySevice? = null
        fun getInstance() = mInstance ?: synchronized(this){
            mInstance ?: MoneySevice().also { mInstance = it }
        }
    }

    private fun createInstance() : MoneyExchangeService{
        val okHttpClient = OkHttpClient.Builder()
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(MONEY_EXCHANGE_LINK)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
        return retrofit.create(MoneyExchangeService::class.java)
    }

    fun getApi() : MoneyExchangeService = api
}
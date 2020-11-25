package com.example.theodoichitieu.view.report

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theodoichitieu.R
import com.example.theodoichitieu.adapter.MyMoneyExchageAdapter
import com.example.theodoichitieu.adapter.MyPayAdapter
import com.example.theodoichitieu.model.MoneyExchange
import com.example.theodoichitieu.model.Pay
import com.example.theodoichitieu.util.MONEY_EXCHANGE_LINK
import com.example.theodoichitieu.viewmodel.MoneyExchangeViewModel
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_money_exchange.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MoneyExchangeActivity : AppCompatActivity() {
    var listMoney= mutableListOf<MoneyExchange>()
    val linearLayoutManager = LinearLayoutManager(this)
    private lateinit var myMoneyExchangeAdapter: MyMoneyExchageAdapter
    lateinit var moneyExchageViewModel:MoneyExchangeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_money_exchange)
        setViewModel()
        setContentWidget()
    }

    private fun setViewModel() {
        moneyExchageViewModel=ViewModelProvider(this).get(MoneyExchangeViewModel::class.java)
    }

    fun setContentWidget() {
        myMoneyExchangeAdapter= MyMoneyExchageAdapter()
        rvMoneyExchange.adapter=myMoneyExchangeAdapter
        rvMoneyExchange.layoutManager=linearLayoutManager
        moneyExchageViewModel.listMoneyExchange.observe(this, Observer {
            it?.let {  myMoneyExchangeAdapter.setList(it) }

        })

    }
}
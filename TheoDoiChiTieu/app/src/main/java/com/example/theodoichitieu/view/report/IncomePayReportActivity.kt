package com.example.theodoichitieu.view.report

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.theodoichitieu.R
import com.example.theodoichitieu.viewmodel.IncomeViewModel
import com.example.theodoichitieu.viewmodel.PayViewModel
import kotlinx.android.synthetic.main.activity_income_pay_report.*

class IncomePayReportActivity : AppCompatActivity() {
    private lateinit var myIncomeViewModel: IncomeViewModel
    private lateinit var myPayViewModel: PayViewModel
    private var totalIncome:Int=100;
    private var totalPay:Int=0
    private  var today:Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_income_pay_report)
        this.today=intent.getLongExtra("today",today)
        getViewModel()
        getData()
        setContentWidget()
    }

    private fun getData() {
        myIncomeViewModel.sumMoneyOfMoth(today).observe(this, Observer {
            it?.let {
                txtSumInCome30Day.text= "$it 000VND"
            }

        })
        myPayViewModel.sumMoneyOfMoth(today).observe(this, Observer {
            it?.let{
                txtSumPay30Day.text="$it 000VND"
            }

        })

    }

    private fun setContentWidget() {

    }

    private fun getViewModel() {
        myPayViewModel= ViewModelProvider(this).get(PayViewModel::class.java)
        myIncomeViewModel=ViewModelProvider(this).get(IncomeViewModel::class.java)
    }
}
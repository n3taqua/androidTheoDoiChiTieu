package com.example.theodoichitieu.view.report

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theodoichitieu.R
import com.example.theodoichitieu.adapter.MyIncomeAdapter
import com.example.theodoichitieu.adapter.MyPayReportAdapter
import com.example.theodoichitieu.util.DAY_NUM_SPACING
import com.example.theodoichitieu.util.previousDay
import com.example.theodoichitieu.viewmodel.PayViewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import kotlinx.android.synthetic.main.activity_pay_report.*


class PayReportActivity : AppCompatActivity() {
    private lateinit var myPayViewModel: PayViewModel
    private  var today:Long=0
    var listData = mutableListOf<Int>()
    val linearLayoutManager = LinearLayoutManager(this)
    private lateinit var myPayReportAdapter: MyPayReportAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pay_report)
        today= intent.getLongExtra("today",today)
        getViewModel()
        setContentWidget()
        getData()

    }

    private fun setContentWidget() {
        myPayReportAdapter= MyPayReportAdapter()
        rvPayReport.adapter=myPayReportAdapter
        rvPayReport.layoutManager=linearLayoutManager
    }

    private fun getData() {
        var daytemp=today
         for (i in 0.. 6){
                myPayViewModel.sumMoneyOfDay(daytemp).observe(this, Observer {
                    it?.let {
                        myPayReportAdapter.add(it)
                    }
                })
            daytemp= previousDay(daytemp)
            }
    }
    private fun getViewModel() {
        myPayViewModel= ViewModelProvider(this).get(PayViewModel::class.java)

    }



}



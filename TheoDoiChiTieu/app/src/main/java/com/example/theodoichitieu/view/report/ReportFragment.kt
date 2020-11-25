package com.example.theodoichitieu.view.report

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.theodoichitieu.R
import kotlinx.android.synthetic.main.fragment_report.*


class ReportFragment(today:Long) : Fragment() {
    val today=today
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setContentWidget()
        setEvent()
    }

    private fun setEvent() {
        txtExchange.setOnClickListener {
            val intent=Intent(activity,MoneyExchangeActivity::class.java)
            startActivity(intent)
        }
        txtCatagoriesList.setOnClickListener {
            val intent=Intent(activity,CatogoriesReportActivity::class.java)
            startActivity(intent)
        }
        txtPayIncomeReportActivity.setOnClickListener {
            val intent=Intent(activity,IncomePayReportActivity::class.java)
            intent.putExtra("today",today)
            startActivity(intent)
        }
        txtInComeReportActivity.setOnClickListener {
            val intent=Intent(activity,IncomeReportActivity::class.java)
            intent.putExtra("today",today)
            startActivity(intent)
        }
        txtPayReportActivity.setOnClickListener {
            val intent=Intent(activity,PayReportActivity::class.java)
            intent.putExtra("today",today)
            startActivity(intent)
        }
        txtInterest.setOnClickListener {
            val intent=Intent(activity,CalculateIntertestActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setContentWidget() {

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_report, container, false)
    }


}

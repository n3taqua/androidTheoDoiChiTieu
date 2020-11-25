package com.example.theodoichitieu.view.intro

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.theodoichitieu.R
import com.example.theodoichitieu.view.report.CalculateIntertestActivity
import com.example.theodoichitieu.viewmodel.IncomeViewModel
import com.example.theodoichitieu.viewmodel.PayViewModel
import kotlinx.android.synthetic.main.fragment_intro.*


class IntroFragment(today:Long) : Fragment() {
    val today=today
    private lateinit var myIncomeViewModel: IncomeViewModel
    private lateinit var myPayViewModel: PayViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_intro, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getVieModel()
        setContent()
    }


    private fun setContent() {
        setSumToday()
        setRecentPayToday()
        setRecentIncomeToday()
        setEvent()

    }

    private fun setEvent() {
        interestActi.setOnClickListener {
            val intent= Intent(activity, CalculateIntertestActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setRecentIncomeToday() {
        myIncomeViewModel.listIncomeOfDay(today).observe(viewLifecycleOwner, Observer {
            when {
                it.size<1 -> {
                    txtTitleIncomeIntroActivity_1.text=" Không có mục "
                    txtValueIncomeIntroActivity_1.text=""

                    txtTitleIncomeIncomeActivity_2.text=""
                    txtValueIncomeIntroActivity_2.text=""
                }
                it.size==1 -> {
                    it[0]?.let {
                        txtTitleIncomeIntroActivity_1.text=it.title
                        val temp=it.money
                        txtValueIncomeIntroActivity_1.text="$temp 000 VND"

                        txtTitleIncomeIncomeActivity_2.text=""
                        txtValueIncomeIntroActivity_2.text=""
                    }
                }
                else -> {
                    it[0]?.let {
                        txtTitleIncomeIntroActivity_1.text=it.title
                        val temp=it.money
                        txtValueIncomeIntroActivity_1.text="$temp 000 VND"
                    }

                    it[1]?.let {
                        txtTitleIncomeIncomeActivity_2.text=it.title
                        val temp=it.money
                        txtValueIncomeIntroActivity_2.text="$temp 000 VND"
                    }

                }
            }
        }
        )
    }

    private fun setRecentPayToday() {
        myPayViewModel.listPayOfDay(today).observe(viewLifecycleOwner, Observer {
            when{
                it.size<1->{
                    txtTitlePayIntroActivity_1.text="Không có mục"
                    txtValuePayIntroActivity_1.text=""

                    txtTitlePayIntroActivity_2.text=""
                    txtValuePayIntroActivity_2.text=""
                }
                it.size==1->{
                    it[0]?.let {
                        txtTitlePayIntroActivity_1.text=it.title
                        val temp=it.money
                        txtValuePayIntroActivity_1.text="$temp 000 VND"

                        txtTitlePayIntroActivity_2.text=""
                        txtValuePayIntroActivity_2.text=""
                    }
                }
                it.size>1->{
                    it[0]?.let {
                        txtTitlePayIntroActivity_1.text=it.title
                        val temp=it.money
                        txtValuePayIntroActivity_1.text="$temp 000 VND"
                    }

                    it[1]?.let {
                        txtTitlePayIntroActivity_2.text=it.title
                        val temp=it.money
                        txtValuePayIntroActivity_2.text="$temp 000 VND"
                    }
                }
            }

        }
        )
    }

    private fun setSumToday() {
        myIncomeViewModel.sumMoneyOfDay(today).observe(viewLifecycleOwner, Observer {
            it?.let {
                txtSumInComeToday.text="$it 000VND"
            }
        })
        myPayViewModel.sumMoneyOfDay(today).observe(viewLifecycleOwner, Observer {
            it?.let {
                txtSumPayToday.text="$it 000VND"
            }
        })
    }

    private fun getVieModel() {
        myPayViewModel= ViewModelProvider(this).get(PayViewModel::class.java)
        myIncomeViewModel= ViewModelProvider(this).get(IncomeViewModel::class.java)
    }


}
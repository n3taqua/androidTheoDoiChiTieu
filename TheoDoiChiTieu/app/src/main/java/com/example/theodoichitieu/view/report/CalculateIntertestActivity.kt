package com.example.theodoichitieu.view.report

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.theodoichitieu.R
import kotlinx.android.synthetic.main.activity_calculate_intertest.*

class CalculateIntertestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculate_intertest)
        setEvent()
    }

    private fun setEvent() {
        btnCal.setOnClickListener {
            val money:Int=txtNumberOfMoney.text.toString().toInt()
            val interest=txtNumberOfInterest.text.toString().toInt()
            val yearNum=txtNumberOfYear.text.toString().toInt()

            var sum:Float=money.toFloat();
            for(i in 0..yearNum-1){
                sum+=sum*interest/100
            }
            txtSumMoneyInterest.text=sum.toString()
        }
    }
}
package com.example.theodoichitieu.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.theodoichitieu.R
import com.example.theodoichitieu.model.Catogories
import com.example.theodoichitieu.model.Income
import com.example.theodoichitieu.model.Pay
import com.example.theodoichitieu.util.fixMonth
import com.example.theodoichitieu.util.intoTwoCharacter
import com.example.theodoichitieu.viewmodel.CatogoriesViewModel
import com.example.theodoichitieu.viewmodel.IncomeViewModel
import com.example.theodoichitieu.viewmodel.PayViewModel

import kotlinx.android.synthetic.main.activity_add_account_line.*
import java.util.*

class AddAccountLineActivity : AppCompatActivity() {
    private lateinit var myIncomeViewModel: IncomeViewModel
    private lateinit var myPayViewModel: PayViewModel
    private lateinit var myCatagoriesViewModel: CatogoriesViewModel
    var  listCata= mutableListOf<Catogories>()
    var listCatagoriesTitle= mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_account_line)
        setContentWidget()
        setEvent()
    }

    @SuppressLint("SetTextI18n")
    private fun setEvent() {
        setEventTxtDate()
        setEventTxtTime()
        setEventBtnSave()
        setIncomePayButton()
    }
    private fun setIncomePayButton() {
        btnPay.setOnCheckedChangeListener{_,_ ->
            btnIncome.isChecked=!btnPay.isChecked
        }
        btnIncome.setOnCheckedChangeListener { buttonView, isChecked ->
            btnPay.isChecked = !btnIncome.isChecked
        }


    }

    private fun setEventBtnSave() {
        btnSave.setOnClickListener {
            val title=txtDetail.text.toString()
            val money=txtMoneyPay.text.toString().toInt()
            val date=txtDate.text.toString().filter { char->char!='/' }.toLong()
            val time=txtTime.text.toString().filter { char->char!=':' }.toLong()
            val cataTitle=getCataBySpin()
            val cataL=listCata.filter {
                it.title==getCataBySpin()
            }
            val cata=cataL[0]
            if(btnIncome.isChecked){
                val accoutLine= Income(title,money,date,time,cata.id)
                myIncomeViewModel.insert(accoutLine)
            }
            else{
                val accoutLine= Pay(title,money,date,time,cata.id)
                myPayViewModel.insert(accoutLine)
            }
            Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun getCataBySpin(): String{
       var value= listCatagoriesTitle[0]
        spinCatagoriesAddAccount.onItemSelectedListener=object :AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
               value=listCatagoriesTitle[position]
            }
        }
        return value
    }

    @SuppressLint("SetTextI18n")
    private fun setEventTxtTime() {
        txtTime.setOnClickListener {
            val currentDay: Date = Calendar.getInstance().time;
            val timeListener=TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->
                txtTime.text="$hourOfDay:$minute"
            }
            val selectedHourOfDay=currentDay.hours
            val selectedMinute=currentDay.minutes
            val timePickerDialog=TimePickerDialog(this,android.R.style.Widget_Material_TimePicker,timeListener,selectedHourOfDay,selectedMinute,true)
            timePickerDialog.show()
        }
    }

    private fun setEventTxtDate() {
        txtDate.setOnClickListener {
            val currentDay: Calendar = Calendar.getInstance();
            val selectedYear=currentDay.get(Calendar.YEAR)
            val selectedMonth=fixMonth(currentDay.get(Calendar.MONTH))
            val selectedDayOfMonth=currentDay.get(Calendar.DAY_OF_MONTH)
            val dateSetListener=DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                val monthFinal=intoTwoCharacter(month.toString())
                txtDate.text="$dayOfMonth/$monthFinal/$year"
            }
            val datePickerDialog=DatePickerDialog(this,android.R.style.Widget_DatePicker,dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth)
            datePickerDialog.show()
        }
    }

    private fun setContentWidget() {
        getVieModel()
        setDateTime()
        setSpinerCatagories()
    }

    private fun setSpinerCatagories() {
        var catagoriesAdapter=ArrayAdapter(this,android.R.layout.simple_list_item_1,listCatagoriesTitle)
        listCata= myCatagoriesViewModel.getAllCatogories().value?:listCata
        myCatagoriesViewModel.getAllCatogories().observe(this, androidx.lifecycle.Observer {
            it?.let {
                listCata=it
                listCata?.forEach {
                    listCatagoriesTitle.add(it.title)
                }
                catagoriesAdapter.notifyDataSetChanged()
            }
        })
        catagoriesAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice)
        spinCatagoriesAddAccount.adapter=catagoriesAdapter
    }

    private fun setDateTime() {
        val currentDay = Calendar.getInstance()
        val dayOfMonth=currentDay.get(Calendar.DAY_OF_MONTH)
        val month= intoTwoCharacter(fixMonth(currentDay.get(Calendar.MONTH)).toString())
        val year=currentDay.get(Calendar.YEAR)
        val hourOfDay=currentDay.get(Calendar.HOUR_OF_DAY)
        val minute=currentDay.get(Calendar.MINUTE)
        txtDate.text="$dayOfMonth/$month/$year"
        txtTime.text="$hourOfDay:$minute"

    }

    private fun getVieModel() {
        myIncomeViewModel= ViewModelProvider(this).get(IncomeViewModel::class.java)
        myPayViewModel= ViewModelProvider(this).get(PayViewModel::class.java)
        myCatagoriesViewModel=ViewModelProvider(this).get(CatogoriesViewModel::class.java)
    }

}

package com.example.theodoichitieu.view.report

import android.app.AlertDialog
import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theodoichitieu.R
import com.example.theodoichitieu.adapter.MyCatogoriesAdapter
import com.example.theodoichitieu.model.Catogories
import com.example.theodoichitieu.util.DAY_NUM_SPACING
import com.example.theodoichitieu.viewmodel.CatogoriesViewModel
import com.example.theodoichitieu.viewmodel.IncomeViewModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.android.synthetic.main.activity_catogories_report.*
import kotlinx.android.synthetic.main.dialog_add_catagories.*

class CatogoriesReportActivity : AppCompatActivity() {
    private lateinit var myCatogoriesVViewModel: CatogoriesViewModel
    private  var today:Long = 0
    private var listData= mutableListOf<Float>()
    val linearLayoutManager = GridLayoutManager(this,2)
    lateinit var catogoriesAdapter:MyCatogoriesAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_catogories_report)
        this.today=intent.getLongExtra("today",today)
        getViewModel()
        getData()
        setEvent()
    }

    private fun setEvent() {
        setRecyclerViewEvent()
        fabAddCatagories.setOnClickListener {
            val dialog=Dialog(this,android.R.style.ThemeOverlay_Material_Dialog)
            dialog.setContentView(R.layout.dialog_add_catagories)
            dialog.btnCancalSaveCatagories.setOnClickListener {
                dialog.cancel()
            }
            dialog.btnSaveCatagories.setOnClickListener {
                val catogoriesName=dialog.txtCatagoriesNameAdd.text.toString()
                val catogoriesDetail=dialog.txtCatagoriesDetailAdd.text.toString()
                val catogories=Catogories(catogoriesName,catogoriesDetail)
                myCatogoriesVViewModel.insert(catogories)
                dialog.cancel()
            }
            dialog.show()
        }
    }

    private fun setRecyclerViewEvent() {
        catogoriesAdapter.listener=object :MyCatogoriesAdapter.OnItemClickListener{
            override fun onItemClick(item: Catogories) {

            }

            override fun onItmeLongClick(item: Catogories) {
                val alertDialog= AlertDialog.Builder(this@CatogoriesReportActivity)
                alertDialog.setTitle(R.string.delete_qa)
                    .setMessage(R.string.delete_qa)
                    .setNegativeButton("No"){ dialog, _ ->
                        dialog.cancel()
                    }
                    //  .setNegativeButtonIcon(context?.getDrawable(R.drawable.ic_cancel))
                    .setPositiveButton("Yes"){ _, _ ->
                        myCatogoriesVViewModel.delete(item)
                    }
                //  .setPositiveButtonIcon(context?.getDrawable(R.drawable.ic_yes))
                alertDialog.show()
            }
        }
    }

    private fun getData() {
        rvCatagories.layoutManager=linearLayoutManager
        catogoriesAdapter=MyCatogoriesAdapter()
        myCatogoriesVViewModel.getAllCatogories().observe(this, Observer {
            it?.let {
                catogoriesAdapter.setList(it)
            }
        })
       rvCatagories.adapter=catogoriesAdapter
    }


    private fun getViewModel() {
        myCatogoriesVViewModel= ViewModelProvider(this).get(CatogoriesViewModel::class.java)
    }


}
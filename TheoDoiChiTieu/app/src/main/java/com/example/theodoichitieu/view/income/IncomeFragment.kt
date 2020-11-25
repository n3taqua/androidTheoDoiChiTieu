package com.example.theodoichitieu.view.income

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.theodoichitieu.R
import com.example.theodoichitieu.adapter.MyIncomeAdapter
import com.example.theodoichitieu.model.Income
import com.example.theodoichitieu.viewmodel.IncomeViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_income.*
import kotlinx.android.synthetic.main.fragment_pay.*

class IncomeFragment(today:Long) : Fragment() {
    val today=today
    var listIncome= mutableListOf<Income>()
    private lateinit var myIncomeViewModel: IncomeViewModel
    private lateinit var myIncomeAdapter: MyIncomeAdapter
    val linearLayoutManager = LinearLayoutManager(activity)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_income, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getVieModel()
        setContent()
    }

    private fun setContent() {
        setRecyclerView()

    }

    private fun setRecyclerView() {
        setRecyclerViewEvent()
    }

    private fun setRecyclerViewEvent() {
            myIncomeAdapter.listener=object :MyIncomeAdapter.OnItemClickListener{
                override fun onItemClick(item: Income) {

                }

                override fun onItmeLongClick(item: Income) {
                    val alertDialog= AlertDialog.Builder(activity)
                    alertDialog.setTitle(R.string.delete_qa)
                        .setMessage(R.string.delete_qa)
                        .setNegativeButton("No"){ dialog, _ ->
                            dialog.cancel()
                        }
                        //  .setNegativeButtonIcon(context?.getDrawable(R.drawable.ic_cancel))
                        .setPositiveButton("Yes"){ _, _ ->
                            myIncomeViewModel.delete(item)
                        }
                    //  .setPositiveButtonIcon(context?.getDrawable(R.drawable.ic_yes))
                    alertDialog.show()
                }
            }
    }

    private fun getVieModel() {
        myIncomeViewModel= ViewModelProvider(this).get(IncomeViewModel::class.java)
        myIncomeAdapter=  MyIncomeAdapter();
        rvIncome.adapter=myIncomeAdapter
        rvIncome.layoutManager=linearLayoutManager
        myIncomeViewModel.listIncomeOfDay(today).observe(viewLifecycleOwner, Observer {
            it?.let { myIncomeAdapter.setListIncome(it)}
        })
    }
}

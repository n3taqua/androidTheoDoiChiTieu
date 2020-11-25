package com.example.theodoichitieu.view.pay

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.theodoichitieu.R
import com.example.theodoichitieu.adapter.MyPayAdapter
import com.example.theodoichitieu.model.Pay
import com.example.theodoichitieu.viewmodel.PayViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.android.synthetic.main.fragment_pay.*


class PayFragment(today:Long) : Fragment() {
    val today=today
    var listPay= mutableListOf<Pay>()
    private lateinit var myPayViewModel: PayViewModel
    private lateinit var myPayAdapter: MyPayAdapter
    val linearLayoutManager = LinearLayoutManager(activity)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pay, container, false)
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
        setContentRecycleView()
        setRecyclerViewEvent()
    }

    private fun setContentRecycleView() {

    }

    override fun onResume() {
        super.onResume()
    }
    private fun setRecyclerViewEvent() {
       myPayAdapter.listener=object :MyPayAdapter.OnItemClickListener{
           override fun onItemClick(item: Pay) {

           }

           override fun onItmeLongClick(item: Pay) {
               val alertDialog=AlertDialog.Builder(activity)
               alertDialog.setTitle(R.string.delete_qa)
                   .setMessage(R.string.delete_qa)
                   .setNegativeButton("No"){ dialog, _ ->
                       dialog.cancel()
                   }
                 //  .setNegativeButtonIcon(context?.getDrawable(R.drawable.ic_cancel))
                   .setPositiveButton("Yes"){ _, _ ->
                       myPayViewModel.delete(item)
                   }
                 //  .setPositiveButtonIcon(context?.getDrawable(R.drawable.ic_yes))
                    alertDialog.show()
           }

       }
    }

    private fun getVieModel() {
        myPayViewModel= ViewModelProvider(this).get(PayViewModel::class.java)
        myPayAdapter=  MyPayAdapter();
        rvPay.adapter=myPayAdapter
        rvPay.layoutManager=linearLayoutManager

        myPayViewModel.listPayOfDay(today).observe(viewLifecycleOwner, Observer {
            it?.let { myPayAdapter.setListPay(it)}
        })

    }
}

package com.example.theodoichitieu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.theodoichitieu.R
import com.example.theodoichitieu.model.Catogories
import com.example.theodoichitieu.model.Pay
import com.example.theodoichitieu.util.MILION

class MyPayReportAdapter: RecyclerView.Adapter<MyPayReportAdapter.MyPayReportViewHolder>()   {
    private  var listData= mutableListOf<Int>()
    class MyPayReportViewHolder(item_view: View):RecyclerView.ViewHolder(item_view) {
        val txtPayReportPecent:TextView=item_view.findViewById(R.id.txtPayReport)
        val txtPayReportPercentPre:TextView=item_view.findViewById(R.id.txtPayReportPre)
        val txtPayReportSum:TextView=item_view.findViewById(R.id.txtPayReportSum)
        val linearLayout:LinearLayout=item_view.findViewById(R.id.layoutPayReport)
    }
    fun add(item:Int){
        this.listData.add(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyPayReportAdapter.MyPayReportViewHolder {
        return MyPayReportViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_pay_report, parent, false)
        )

    }

    override fun getItemCount(): Int {
       return listData.size
    }

    override fun onBindViewHolder(holder: MyPayReportAdapter.MyPayReportViewHolder, position: Int) {
        holder.txtPayReportSum.text=listData[position].toString()
        val maxValue: Int =listData.max()?:1
        val valuepercent=(listData[position])

        holder.linearLayout.weightSum= maxValue.toFloat()
        val layoutParam=LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,
            valuepercent.toFloat()
        )
        val layoutParam2=LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT,
            (maxValue-valuepercent).toFloat()
        )
        holder.txtPayReportPecent.layoutParams=layoutParam
        holder.txtPayReportPercentPre.layoutParams=layoutParam2
    }
}
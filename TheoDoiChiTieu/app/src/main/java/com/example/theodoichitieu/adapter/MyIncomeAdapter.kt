package com.example.theodoichitieu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.example.theodoichitieu.R
import com.example.theodoichitieu.model.Income
import com.example.theodoichitieu.util.getRandomColor
import kotlinx.android.synthetic.main.item_income_list.view.*

class MyIncomeAdapter: RecyclerView.Adapter<MyIncomeAdapter.MyIncomeViewHolder>()  {
    private  var listIncome= mutableListOf<Income>()
    var listener: OnItemClickListener? =null
    interface OnItemClickListener {
        fun onItemClick(item: Income)
        fun onItmeLongClick(item: Income)
    }

    fun setListIncome(listIncome: MutableList<Income>){
        this.listIncome=listIncome
        notifyDataSetChanged()
    }

    class MyIncomeViewHolder(item_view: View):RecyclerView.ViewHolder(item_view) {
        val txtTitleIncome: TextView =item_view.findViewById(R.id.txtTitleIncome)
        val txtValueIncome: TextView =item_view.findViewById(R.id.txtValueIncome)
        val imgLetterIconIncome:ImageView=item_view.findViewById(R.id.imgLetterIconIncome)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyIncomeViewHolder {
       return  MyIncomeViewHolder(
           LayoutInflater.from(parent.context).inflate(R.layout.item_income_list,parent,false)
       )
    }

    override fun getItemCount(): Int {
         return  listIncome.size
    }

    override fun onBindViewHolder(holder: MyIncomeViewHolder, position: Int) {
        holder.txtTitleIncome.text=listIncome[position].title
        holder.txtValueIncome.text=listIncome[position].money.toString()+"000VND"
        holder.itemView.setOnClickListener {
            listener?.onItemClick(listIncome[position])
        }
        holder.itemView.setOnLongClickListener{
            listener?.onItmeLongClick(listIncome[position])
            return@setOnLongClickListener  true
        }
        val drawable = TextDrawable.builder()
            .buildRound(listIncome[position].title[0].toString(), getRandomColor())
        holder.imgLetterIconIncome.setImageDrawable(drawable)
    }
}
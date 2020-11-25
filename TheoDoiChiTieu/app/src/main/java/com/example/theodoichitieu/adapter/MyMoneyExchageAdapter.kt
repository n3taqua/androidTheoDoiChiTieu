package com.example.theodoichitieu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.theodoichitieu.R
import com.example.theodoichitieu.model.MoneyExchange
import kotlinx.android.synthetic.main.item_money_exchange.view.*

class MyMoneyExchageAdapter: RecyclerView.Adapter<MyMoneyExchageAdapter.MyMoneyExchageViewHolder>() {
    var listMoneyExchange= mutableListOf<MoneyExchange>()
    class MyMoneyExchageViewHolder(item_view: View):RecyclerView.ViewHolder(item_view) {
        val txtMoneyBuy:TextView=item_view.findViewById(R.id.txtMoneyPay)
        val txtMoneySell:TextView=item_view.findViewById(R.id.txtMoneySell);
        val txtMoneyId:TextView=item_view.findViewById(R.id.txtMoneyId)
        val imgMoney:ImageView=item_view.findViewById(R.id.imgMoney)
    }

    fun setList(listMoney:MutableList<MoneyExchange>){
        this.listMoneyExchange=listMoney
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyMoneyExchageViewHolder {
        return MyMoneyExchageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_money_exchange, parent, false)
        )
    }

    override fun getItemCount(): Int {
       return listMoneyExchange.size
    }

    override fun onBindViewHolder(holder: MyMoneyExchageViewHolder, position: Int) {
        holder.txtMoneyBuy.text=listMoneyExchange[position].muatienmat
        holder.txtMoneySell.text=listMoneyExchange[position].bantienmat
        holder.txtMoneyId.text=listMoneyExchange[position].type
        Glide.with(holder.imgMoney).load(listMoneyExchange[position].imageurl).into(holder.imgMoney)

    }
}
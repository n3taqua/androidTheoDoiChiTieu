package com.example.theodoichitieu.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.example.theodoichitieu.R
import com.example.theodoichitieu.model.Pay
import com.example.theodoichitieu.util.getRandomColor


class MyPayAdapter:RecyclerView.Adapter<MyPayAdapter.MyPayViewHolder>()   {
    private  var listPay= mutableListOf<Pay>()
    class MyPayViewHolder(item_view: View): RecyclerView.ViewHolder(item_view) {
        val txtTitlePay: TextView =item_view.findViewById(R.id.txtTitlePay)
        val txtValuePay:TextView=item_view.findViewById(R.id.txtValuePay)
        val imgLetterIconPay:ImageView=item_view.findViewById(R.id.imgLetterIconPay)
        val cardView:CardView=item_view.findViewById(R.id.cardItemPayList)
    }

    var listener: OnItemClickListener? =null
    interface OnItemClickListener {
        fun onItemClick(item: Pay)
        fun onItmeLongClick(item: Pay)
    }

    fun setListPay(listPay: MutableList<Pay>){
        this.listPay=listPay
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPayViewHolder {
        return  MyPayViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_pay_list,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return  listPay.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyPayViewHolder, position: Int) {
        holder.txtTitlePay.text=listPay[position].title
        holder.txtValuePay.text=listPay[position].money.toString()+"000VND"
        holder.itemView.setOnClickListener {
            listener?.onItemClick(listPay[position])
        }
        holder.itemView.setOnLongClickListener{
            listener?.onItmeLongClick(listPay[position])
            return@setOnLongClickListener  true
        }

        val drawable = TextDrawable.builder()
            .buildRound(listPay[position].title[0].toString(), getRandomColor())
        holder.imgLetterIconPay.setImageDrawable(drawable)

    }
}
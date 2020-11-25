package com.example.theodoichitieu.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import com.example.theodoichitieu.R
import com.example.theodoichitieu.model.Catogories
import com.example.theodoichitieu.model.Income
import com.example.theodoichitieu.util.getRandomColor

class MyCatogoriesAdapter: RecyclerView.Adapter<MyCatogoriesAdapter.MyCatogoriesViewHolder>()  {
    private  var listCatogories= mutableListOf<Catogories>()
    var listener: OnItemClickListener? =null
    interface OnItemClickListener {
        fun onItemClick(item: Catogories)
        fun onItmeLongClick(item: Catogories)
    }

    fun setList(listCatogories: MutableList<Catogories>){
        this.listCatogories=listCatogories
        notifyDataSetChanged()
    }
    class MyCatogoriesViewHolder (item_view: View):RecyclerView.ViewHolder(item_view){
        val txtCategoriesTitle:TextView=item_view.findViewById(R.id.txtCatogoreisTitle)
        val txtCategoriesDetail:TextView=item_view.findViewById(R.id.txtCatogoreisDetail)
        val imgCata:ImageView=item_view.findViewById(R.id.img_film_poster_grid)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyCatogoriesViewHolder {
        return MyCatogoriesViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_catogories_list, parent, false)
        )
    }

    override fun getItemCount(): Int {
       return listCatogories.size
    }

    override fun onBindViewHolder(holder: MyCatogoriesViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            listener?.onItemClick(listCatogories[position])
        }
        holder.itemView.setOnLongClickListener{
            listener?.onItmeLongClick(listCatogories[position])
            return@setOnLongClickListener  true
        }
        holder.txtCategoriesTitle.text=listCatogories[position].title
        holder.txtCategoriesDetail.text=listCatogories[position].detail
        val drawable = TextDrawable.builder()
            .buildRect(listCatogories[position].title[0].toString(), getRandomColor())
        holder.imgCata.setImageDrawable(drawable)
    }
}
package com.example.dindinnassignment.ui.orders.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dindinnassignment.R
import com.example.dindinnassignment.datamodels.OrderInfo

class IngredientsAdapter(private val addons : List<OrderInfo.Data.Addon>) : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        private val tvQuantity = itemView.findViewById<TextView>(R.id.tvQuantity)
        private val tvName = itemView.findViewById<TextView>(R.id.tvName)

        fun bindView(addon: OrderInfo.Data.Addon){
            tvQuantity.text = "x"+addon.quantity.toString()
            tvName.text = addon.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.ingredients_list_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(addons[position])
    }

    override fun getItemCount(): Int {
        return addons.size
    }
}
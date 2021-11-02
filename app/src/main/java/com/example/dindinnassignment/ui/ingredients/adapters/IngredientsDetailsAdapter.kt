package com.example.dindinnassignment.ui.ingredients.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dindinnassignment.R
import com.example.dindinnassignment.datamodels.IngredientsInfo

class IngredientsDetailsAdapter(private val list : List<IngredientsInfo.Ingredient>) : RecyclerView.Adapter<IngredientsDetailsAdapter.ViewHolder>() {
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        private val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        private val tvAvailableCount = itemView.findViewById<TextView>(R.id.tvAvailableCount)

        fun bind(ingredientsInfo: IngredientsInfo.Ingredient){
            tvTitle.text = ingredientsInfo.name
            tvAvailableCount.text = ingredientsInfo.stock.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.grid_list_item,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }
}
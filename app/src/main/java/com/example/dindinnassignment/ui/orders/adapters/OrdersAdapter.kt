package com.example.dindinnassignment.ui.orders.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.dindinnassignment.datamodels.OrderInfo

import android.widget.ProgressBar
import com.example.dindinnassignment.DATE_FORMAT
import com.example.dindinnassignment.R
import com.example.dindinnassignment.TIME_FORMAT
import com.example.dindinnassignment.parseDate


class OrdersAdapter(
    private var list: List<OrderInfo.Data>,
    private val orderActionListeners: OrderActionListeners
) : RecyclerView.Adapter<OrdersAdapter.ViewHolder>() {
    class ViewHolder(
        itemView: View,
        private val context: Context,
        private val orderActionListeners: OrderActionListeners
    ) : RecyclerView.ViewHolder(itemView) {

        private val tvOrderNumber = itemView.findViewById<TextView>(R.id.tvOrderNumber)
        private val tvCreatedAt = itemView.findViewById<TextView>(R.id.tvCreatedAt)
        private val tvAutoReject = itemView.findViewById<TextView>(R.id.tvAutoReject)
        private val btButton = itemView.findViewById<Button>(R.id.btButton)
        private val rvIngredients = itemView.findViewById<RecyclerView>(R.id.rvIngredients)
        private val tvItems = itemView.findViewById<TextView>(R.id.tvItems)
        private val progressBar = itemView.findViewById<ProgressBar>(R.id.progressBar)
        private val tvAutoRejectLabel = itemView.findViewById<TextView>(R.id.tvAutoRejectLabel)

        fun bindView(data: OrderInfo.Data) {
            tvOrderNumber.text = "# " + data.id.toString()

            val adapter = IngredientsAdapter(data.addon)
            rvIngredients.adapter = adapter

            tvCreatedAt.text = context.getString(R.string.at) +" "+ parseDate(
                DATE_FORMAT,
                TIME_FORMAT,
                data.created_at
            )

            val itemsCount = data.addon.size
            tvItems.text = if (itemsCount > 1) {
                "${data.addon.size}" + " " + context.getString(R.string.items)
            } else "${data.addon.size}" + " " + context.getString(R.string.items)

            progressBar.progress = data.progress

            tvAutoReject.text = context.getString(R.string.auto_reject)+" " + data.remainingTime

            if (data.progress == 100) {
                tvAutoRejectLabel.visibility = View.INVISIBLE
                tvAutoReject.visibility = View.INVISIBLE
                progressBar.visibility = View.INVISIBLE
                btButton.background = context.getDrawable(R.drawable.round_button_okay)
                btButton.setTextColor(Color.BLACK)
                btButton.text = context.getString(R.string.okay)

                btButton.setOnClickListener {
                    orderActionListeners.onOkay(data)
                }
            } else {
                tvAutoReject.visibility = View.VISIBLE
                progressBar.visibility = View.VISIBLE
                tvAutoRejectLabel.visibility = View.VISIBLE
                btButton.background = context.getDrawable(R.drawable.round_button_accept)
                btButton.setTextColor(Color.WHITE)
                btButton.text = context.getString(R.string.accept)

                btButton.setOnClickListener {
                    orderActionListeners.onAccept(data)
                }
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.orders_list_item, parent, false)
        return ViewHolder(itemView, parent.context, orderActionListeners)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun update(newList: List<OrderInfo.Data>) {
        list = newList
        notifyDataSetChanged()
    }
}

interface OrderActionListeners {
    fun onAccept(data: OrderInfo.Data)
    fun onOkay(data: OrderInfo.Data)
}
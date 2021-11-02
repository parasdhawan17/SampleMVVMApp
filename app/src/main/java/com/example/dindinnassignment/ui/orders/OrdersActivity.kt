package com.example.dindinnassignment.ui.orders

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dindinnassignment.*
import com.example.dindinnassignment.datamodels.OrderInfo
import com.example.dindinnassignment.databinding.ActivityMainBinding
import com.example.dindinnassignment.ui.ingredients.IngredientsActivity
import com.example.dindinnassignment.ui.orders.adapters.OrderActionListeners
import com.example.dindinnassignment.ui.orders.adapters.OrdersAdapter
import com.example.dindinnassignment.ui.orders.repositories.OrdersRepository


class OrdersActivity : AppCompatActivity() {

    private val repository = OrdersRepository(ServiceLocator.getApiService())

    val viewModel : OrdersViewModel by viewModels { OrdersViewModelFactory(repository,application) }

    private lateinit var orderAdapter : OrdersAdapter

    private var orders = listOf<OrderInfo.Data>()

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListView()

        viewModel.ordersLiveData.observe(this, Observer {
            orderAdapter.update(it.data)
        })

        binding.ivAdd.setOnClickListener {
            val intent = Intent(this, IngredientsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setListView(){
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.rvOrders.layoutManager = layoutManager
        orderAdapter = OrdersAdapter(orders,object : OrderActionListeners {
            override fun onAccept(data: OrderInfo.Data) {
                viewModel.accept(data)
            }

            override fun onOkay(data: OrderInfo.Data) {
                viewModel.okay(data)
            }

        })
        binding.rvOrders.adapter = orderAdapter
    }

}
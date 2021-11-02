package com.example.dindinnassignment.datamodels

data class OrderInfo(
    val data: ArrayList<Data>,
    val status: Status
) {
    data class Data(
        val addon: ArrayList<Addon>,
        val alerted_at: String,
        val created_at: String,
        val expired_at: String,
        val id: Int,
        val quantity: Int,
        val title: String
    ) {
        var progress : Int = 0
        var remainingTime : String = ""

        data class Addon(
            val id: Int,
            val quantity: Int,
            val title: String
        )
    }

    data class Status(
        val message: String,
        val statusCode: Int,
        val success: Boolean
    )
}
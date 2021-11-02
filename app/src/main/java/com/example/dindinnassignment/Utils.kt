package com.example.dindinnassignment

import kotlinx.coroutines.delay
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import kotlinx.coroutines.flow.*

fun parseDate(inputPattern : String, outputPattern : String,time: String): String? {
    val inputFormat = SimpleDateFormat(inputPattern)
    val outputFormat = SimpleDateFormat(outputPattern)
    var date: Date? = null
    var str: String? = null
    try {
        date = inputFormat.parse(time)
        str = outputFormat.format(date)
    } catch (e: ParseException) {
        e.printStackTrace()
    }
    return str
}

fun differenceInTime(inputPattern : String,startTime: String,endTime: String): Long? {
    val inputFormat = SimpleDateFormat(inputPattern)
    return try {
        val startDate = inputFormat.parse(startTime)
        val endDate = inputFormat.parse(endTime)

        endDate.time - startDate.time

    } catch (e: ParseException) {
        e.printStackTrace()
        null
    }
}

fun convertMillsToTime(milliseconds : Long) : String{
    val hours = TimeUnit.MILLISECONDS.toHours(milliseconds)
    val mins =  TimeUnit.MILLISECONDS.toMinutes(milliseconds) -
            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliseconds))
    val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds) -
            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliseconds))

    var time = ""
    if(hours.toInt()!=0) time += String.format("%2dh",hours)
    if(mins.toInt()!=0) time += String.format("%2dm",mins)
    if(seconds.toInt()!=0) time += String.format("%2ds",seconds)

    return time
}
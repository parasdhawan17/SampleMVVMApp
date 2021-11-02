package com.example.dindinnassignment.ui.orders

import android.app.Application
import com.example.dindinnassignment.datamodels.OrderInfo
import kotlinx.coroutines.launch
import android.media.MediaPlayer
import android.provider.Settings
import androidx.lifecycle.*
import com.example.dindinnassignment.*
import com.example.dindinnassignment.ui.orders.repositories.BaseOrdersRepository
import com.example.dindinnassignment.ui.orders.repositories.OrdersRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.ensureActive
import kotlinx.coroutines.flow.*


class OrdersViewModel(
    private val ordersRepository: BaseOrdersRepository,
    application: Application
) : AndroidViewModel(application) {

    private val _ordersLiveData = MutableLiveData<OrderInfo>()
    val ordersLiveData = _ordersLiveData

    val mediaPlayerHashMap = HashMap<Int, MediaPlayer>()
    val countDownTimersMap = HashMap<Int, Job>()

    init {
        viewModelScope.launch {
            val response = ordersRepository.fetchOrders()
            _ordersLiveData.value = response
            for (obj in response.data) {
                startTimer(obj)
            }
        }
    }

    fun accept(data: OrderInfo.Data) {
        countDownTimersMap[data.id]?.cancel()
        _ordersLiveData.value?.data?.remove(data)
        _ordersLiveData.postValue(ordersLiveData.value)
        stopRingTone(data)
    }

    fun okay(data: OrderInfo.Data) {
        _ordersLiveData.value?.data?.remove(data)
        _ordersLiveData.postValue(ordersLiveData.value)
    }

    private fun startTimer(data: OrderInfo.Data){
        val expiryDiff = differenceInTime(DATE_FORMAT, data.created_at, data.expired_at)
            ?: return

        val notificationDiff = differenceInTime(DATE_FORMAT, data.alerted_at, data.expired_at)
            ?: return


        val timerIntent = FlowTimer(viewModelScope)
        val timerStateFlow: StateFlow<TimerState> = timerIntent.timerStateFlow
        timerIntent.toggleTime(expiryDiff.toInt()/1000)

        var i = 0
        var isNotificationFired = false

        val job = viewModelScope.launch {
            ensureActive()
            timerStateFlow.collect {
                i++
                data.progress = (i as Int * 100 / (expiryDiff / 1000)).toInt()
                data.remainingTime = convertMillsToTime((it.secondsRemaining!!*1000).toLong())
                _ordersLiveData.postValue(_ordersLiveData.value)

                if(it.secondsRemaining == (notificationDiff/1000).toInt() && !isNotificationFired){
                    fireRingtone(data)
                    isNotificationFired = true
                }

                if(data.progress>=100){
                    stopRingTone(data)
                    cancel()
                }
            }
        }

        countDownTimersMap[data.id] = job
    }

    fun fireRingtone(data: OrderInfo.Data) {
        val player: MediaPlayer = MediaPlayer.create(
            getApplication(),
            Settings.System.DEFAULT_RINGTONE_URI
        )
        player.start()
        mediaPlayerHashMap[data.id] = player
    }

    fun stopRingTone(data: OrderInfo.Data){
        mediaPlayerHashMap[data.id]?.stop()
    }

    override fun onCleared() {
        clean()
        super.onCleared()
    }

    fun clean(){
        for ((key, value) in countDownTimersMap) {
            value.cancel()
        }
        for ((key, value) in mediaPlayerHashMap) {
            value.stop()
        }
    }
}


class OrdersViewModelFactory(
    private val ordersRepository: OrdersRepository,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(OrdersViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return OrdersViewModel(ordersRepository, application) as T
        }
        throw IllegalArgumentException("Unable to construct viewmodel")
    }
}
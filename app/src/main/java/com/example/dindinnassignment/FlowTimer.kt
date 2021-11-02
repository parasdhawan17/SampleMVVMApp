package com.example.dindinnassignment

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class FlowTimer(private val timerScope: CoroutineScope) {

    private var _timerStateFlow = MutableStateFlow(TimerState())
    val timerStateFlow: StateFlow<TimerState> = _timerStateFlow

    private var job: Job? = null

    fun toggleTime(totalSeconds: Int) {
        job = if (job == null) {
            timerScope.launch {
                initTimer(totalSeconds)
                    .onCompletion { _timerStateFlow.emit(TimerState()) }
                    .collect { _timerStateFlow.emit(it) }
            }
        } else {
            job?.cancel()
            null
        }
    }

    private fun initTimer(totalSeconds: Int): Flow<TimerState> =
        (totalSeconds - 1 downTo 0).asFlow()
            .onEach { delay(1000) }
            .onStart { emit(totalSeconds) }
            .conflate()
            .transform { remainingSeconds: Int ->
                emit(TimerState(remainingSeconds, totalSeconds))
            }
}

data class TimerState(
    val secondsRemaining: Int? = null,
    val totalSeconds: Int? = null,
) {

    override fun toString(): String =
        "Seconds Remaining $secondsRemaining, totalSeconds: $totalSeconds"

}

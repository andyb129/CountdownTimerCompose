package uk.co.barbuzz.countdowntimer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class CountdownViewModel: ViewModel() {
    
    private val _timer: MutableLiveData<Int> = MutableLiveData(0)
    val timer: LiveData<Int> = _timer

    private val _timerStart: MutableLiveData<Int> = MutableLiveData(0)
    val timerStart: LiveData<Int> = _timerStart

    private var timerRunning: Boolean = false
    private var timerCurrent: Int = 0

    fun countdownFlow() = flow {
        if (timerRunning) {
            val timerStartValue = _timer.value ?: 0
            for (i in timerStartValue downTo 0) {
                if (timerRunning) {
                    timerCurrent = i
                    delay(1000L)
                    emit(i)
                } else {
                    break
                }
            }
            if (timerCurrent == 0) {
                resetTimer()
            }
        }
    }

    fun incrementTimer(increment: Int) {
        _timerStart.value = _timerStart.value?.plus(increment)
    }

    fun decrementTimer(decrement: Int) {
        if (_timerStart.value != null && _timerStart.value!! > 0) {
            _timerStart.value = _timerStart.value?.minus(decrement)
        }
    }

    fun startTimer() {
        timerRunning = true
        if (timerCurrent > 0) {
            _timer.value = timerCurrent
        } else {
            _timer.value = _timerStart.value
        }
    }
    
    fun pauseTimer() {
        timerRunning = false
    }

    fun resetTimer() {
        timerCurrent = 0
        _timer.value = 0
        _timerStart.value = 0
        timerRunning = false
    }
}

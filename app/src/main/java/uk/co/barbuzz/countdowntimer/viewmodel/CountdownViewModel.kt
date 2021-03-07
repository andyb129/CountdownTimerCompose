package uk.co.barbuzz.countdowntimer.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class CountdownViewModel: ViewModel() {
    
    private val _timer: MutableLiveData<Int> = MutableLiveData(0)
    val timer: LiveData<Int> = _timer

    private val _timerStartSec: MutableLiveData<Int> = MutableLiveData(0)
    val timerStartSec: LiveData<Int> = _timerStartSec

    private val _timerStartMin: MutableLiveData<Int> = MutableLiveData(0)
    val timerStartMin: LiveData<Int> = _timerStartMin

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

    fun incrementSecondTimer(increment: Int) {
        _timerStartSec.value = _timerStartSec.value?.plus(increment)
    }

    fun decrementSecondTimer(decrement: Int) {
        if (_timerStartSec.value != null && _timerStartSec.value!! > 0) {
            _timerStartSec.value = _timerStartSec.value?.minus(decrement)
        }
    }

    fun incrementMinuteTimer(increment: Int) {
        _timerStartSec.value = _timerStartSec.value?.plus(increment)
    }

    fun decrementMinuteTimer(decrement: Int) {
        if (_timerStartSec.value != null && _timerStartSec.value!! > 0) {
            _timerStartSec.value = _timerStartSec.value?.minus(decrement)
        }
    }

    fun startTimer() {
        timerRunning = true
        if (timerCurrent > 0) {
            _timer.value = timerCurrent
        } else {
            _timer.value = _timerStartSec.value
        }
    }
    
    fun pauseTimer() {
        timerRunning = false
    }

    fun resetTimer() {
        timerCurrent = 0
        _timer.value = 0
        _timerStartSec.value = 0
        timerRunning = false
    }
}

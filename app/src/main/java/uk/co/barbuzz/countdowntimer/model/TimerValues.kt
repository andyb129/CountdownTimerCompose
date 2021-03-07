package uk.co.barbuzz.countdowntimer.model

import kotlinx.coroutines.flow.Flow

data class TimerValues (
    val flow: Flow<Int>,
    val timer: Int,
    val timerStartMin: Int,
    val timerStartSec: Int,
    val startTimer: () -> Unit,
    val pauseTimer: () -> Unit,
    val resetTimer: () -> Unit,
    val decrementMinuteOneSecond: () -> Unit,
    val incrementMinuteOneSecond: () -> Unit,
    val decrementSecondOneSecond: () -> Unit,
    val incrementSecondOneSecond: () -> Unit,
    val incrementTimerByFiveSeconds: () -> Unit,
    val incrementTimerByTenSeconds: () -> Unit
)

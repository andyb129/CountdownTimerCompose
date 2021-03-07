package uk.co.barbuzz.countdowntimer.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import uk.co.barbuzz.countdowntimer.R

@Composable
fun TimerScreen(
    flow: Flow<Int>,
    timer: Int,
    timerStart: Int,
    startTimer: () -> Unit,
    pauseTimer: () -> Unit,
    resetTimer: () -> Unit,
    decrementTimerByOneSeconds: () -> Unit,
    incrementTimerByOneSeconds: () -> Unit,
    incrementTimerByFiveSeconds: () -> Unit,
    incrementTimerByTenSeconds: () -> Unit
) {
    Box (
        modifier = Modifier.fillMaxSize(),
    ) {
        Image(
            painter = painterResource(id = R.drawable.clock),
            contentDescription = null,
            contentScale = ContentScale.FillBounds)
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Countdown(flow, timerStart, timer)
            Spacer(Modifier.height(32.dp).fillMaxWidth())
            Timer(
                startTimer,
                pauseTimer,
                resetTimer,
                decrementTimerByOneSeconds,
                incrementTimerByOneSeconds,
                incrementTimerByFiveSeconds,
                incrementTimerByTenSeconds
            )
        }
    }
}

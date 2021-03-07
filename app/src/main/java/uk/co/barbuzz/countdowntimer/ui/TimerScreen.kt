package uk.co.barbuzz.countdowntimer.ui

import androidx.compose.foundation.Image
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
import uk.co.barbuzz.countdowntimer.model.TimerValues

@Composable
fun TimerScreen(timerValues: TimerValues) {
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
            Countdown(
                timerValues.flow,
                timerValues.timerStartSec,
                timerValues.timer,
                timerValues.decrementMinuteOneSecond,
                timerValues.incrementMinuteOneSecond,
                timerValues.decrementSecondOneSecond,
                timerValues.incrementSecondOneSecond
            )
            Spacer(Modifier.height(32.dp).fillMaxWidth())
            Timer(
                timerValues.startTimer,
                timerValues.pauseTimer,
                timerValues.resetTimer,
                timerValues.incrementTimerByFiveSeconds,
                timerValues.incrementTimerByTenSeconds
            )
        }
    }
}

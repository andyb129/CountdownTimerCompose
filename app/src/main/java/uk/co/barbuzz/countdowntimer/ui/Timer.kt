package uk.co.barbuzz.countdowntimer.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Timer(startTimer: () -> Unit,
          pauseTimer: () -> Unit,
          resetTimer: () -> Unit,
          decrementTimerByOneSeconds: () -> Unit,
          incrementTimerByOneSeconds: () -> Unit,
          incrementTimerByFiveSeconds: () -> Unit,
          incrementTimerByTenSeconds: () -> Unit
) {
    Column(Modifier.fillMaxWidth().padding(16.dp)) {
        Row (
            Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            TimerButton("Start", startTimer)
            TimerButton("Pause", pauseTimer)
            TimerButton("Reset", resetTimer)
        }
        Row (
            Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            TimerButton("-", decrementTimerByOneSeconds)
            TimerButton("+5", incrementTimerByFiveSeconds)
            TimerButton("+10", incrementTimerByTenSeconds)
            TimerButton("+", incrementTimerByOneSeconds)
        }
    }
}

@Composable
private fun TimerButton(buttonText: String, buttonOnClick: () -> Unit) {
    Button(onClick = buttonOnClick, modifier = Modifier.padding(8.dp)) {
        Text(buttonText)
    }
}

@Preview(showBackground = true)
@Composable
fun TimerPreview() {
    //Timer(10, )
}

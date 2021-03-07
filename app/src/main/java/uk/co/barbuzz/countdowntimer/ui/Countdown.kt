package uk.co.barbuzz.countdowntimer.ui

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.flow.Flow
import uk.co.barbuzz.countdowntimer.ui.theme.purple500
import uk.co.barbuzz.countdowntimer.ui.theme.teal200

@Composable
fun Countdown(
    flow: Flow<Int>,
    timerStart: Int,
    timer: Int
) {
    val countDownValue by flow.collectAsState(initial = timer)
    val transition = updateTransition(targetState = countDownValue)
    val colour by transition.animateColor() { state ->
        if (state % 2 != 0) {
            teal200
        } else {
            purple500
        }
    }
    val size by transition.animateDp() { state ->
        if (state % 2 != 0) {
            76.dp
        } else {
            96.dp
        }
    }

    Column(Modifier.height(150.dp)) {
        when (countDownValue) {
            in 1..timer -> {
                CountdownText(countDownValue.toString(), colour, size)
            }
            else -> {
                CountdownText(timerStart.toString(), Color.White, size)
            }
        }
    }
}

@Composable
fun CountdownText(text: String, color: Color = Color.Black, size: Dp = 56.dp) {
    Text(
        text = text,
        color = color,
        style = TextStyle(
            fontSize = with(LocalDensity.current) {
                size.toSp()
            },
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Bold
        )
    )
}

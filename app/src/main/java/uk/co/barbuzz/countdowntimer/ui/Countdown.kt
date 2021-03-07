/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
import uk.co.barbuzz.countdowntimer.ui.theme.purple200
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
            purple200
        }
    }
    val size by transition.animateDp() { state ->
        if (state % 2 != 0) {
            96.dp
        } else {
            106.dp
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

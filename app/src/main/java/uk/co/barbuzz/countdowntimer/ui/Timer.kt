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

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Timer(
    startTimer: () -> Unit,
    pauseTimer: () -> Unit,
    resetTimer: () -> Unit,
    decrementTimerByOneSeconds: () -> Unit,
    incrementTimerByOneSeconds: () -> Unit,
    incrementTimerByFiveSeconds: () -> Unit,
    incrementTimerByTenSeconds: () -> Unit
) {
    Column(Modifier.fillMaxWidth().padding(16.dp)) {
        Row(
            Modifier.fillMaxWidth().padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            TimerButton("Start", startTimer)
            TimerButton("Pause", pauseTimer)
            TimerButton("Reset", resetTimer)
        }
        Row(
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

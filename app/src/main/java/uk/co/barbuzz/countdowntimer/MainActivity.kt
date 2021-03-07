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
package uk.co.barbuzz.countdowntimer

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import uk.co.barbuzz.countdowntimer.model.TimerValues
import uk.co.barbuzz.countdowntimer.ui.AppBarScaffold
import uk.co.barbuzz.countdowntimer.ui.TimerScreen
import uk.co.barbuzz.countdowntimer.ui.theme.MyTheme
import uk.co.barbuzz.countdowntimer.viewmodel.CountdownViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp(countdownViewModel: CountdownViewModel = viewModel()) {
    Surface(color = MaterialTheme.colors.background) {
        val timer by countdownViewModel.timer.observeAsState(0)
        val timerStartSec by countdownViewModel.timerStartSec.observeAsState(0)
        val timerStartMin by countdownViewModel.timerStartMin.observeAsState(0)
        AppBarScaffold(screenTitle = "Countdown Timer") {
            TimerScreen(
                getTimerValues(countdownViewModel, timer, timerStartSec, timerStartMin)
            )
        }
    }
}

fun getTimerValues(
    countdownViewModel: CountdownViewModel,
    timer: Int,
    timerStartMin: Int,
    timerStartSec: Int
): TimerValues =
    TimerValues(
        flow = countdownViewModel.countdownFlow(),
        timer = timer,
        timerStartMin = timerStartMin,
        timerStartSec = timerStartSec,
        startTimer = { countdownViewModel.startTimer() },
        pauseTimer = { countdownViewModel.pauseTimer() },
        resetTimer = { countdownViewModel.resetTimer() },
        decrementMinuteOneSecond = {
            countdownViewModel.decrementMinuteTimer(decrement = 1)
        },
        incrementMinuteOneSecond = {
            countdownViewModel.incrementMinuteTimer(increment = 1)
        },
        decrementSecondOneSecond = {
            countdownViewModel.decrementSecondTimer(decrement = 1)
        },
        incrementSecondOneSecond = {
            countdownViewModel.incrementSecondTimer(increment = 1)
        },
        incrementTimerByFiveSeconds = {
            countdownViewModel.incrementSecondTimer(increment = 5)
        },
        incrementTimerByTenSeconds = {
            countdownViewModel.incrementSecondTimer(increment = 10)
        }
    )

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}

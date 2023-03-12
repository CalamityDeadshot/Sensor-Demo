package ru.itschool.sensordemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.itschool.sensordemo.presentation.SensorViewModel
import ru.itschool.sensordemo.presentation.theme.SensorDemoTheme

class MainActivity : ComponentActivity() {
    val viewModel: SensorViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SensorDemoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Row(
                        modifier = Modifier
                            .padding(16.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .width(IntrinsicSize.Max)
                        ) {
                            Text(text = "Lux: ")
                            Text(text = "Acceleration: ")
                        }

                        Column {
                            Text(text = viewModel.lux.toString())

                            Text(text = "X: ${viewModel.acceleration[0]}")
                            Text(text = "Y: ${viewModel.acceleration[1]}")
                            Text(text = "Z: ${viewModel.acceleration[2]}")

                        }
                    }
                }
            }
        }
    }
}
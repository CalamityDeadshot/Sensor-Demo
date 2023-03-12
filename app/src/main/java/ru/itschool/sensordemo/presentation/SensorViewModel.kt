package ru.itschool.sensordemo.presentation

import android.app.Application
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import ru.itschool.sensordemo.data.sensor.AccelerationSensor
import ru.itschool.sensordemo.data.sensor.LightSensor

class SensorViewModel(
    application: Application
): AndroidViewModel(application) {

    private val lightSensor = LightSensor(application)

    private val accelerometer = AccelerationSensor(application)

    var lux by mutableStateOf(0f)
        private set

    var acceleration by mutableStateOf(listOf(0f, 0f, 0f))
        private set

    init {
        lightSensor.apply {
            startListening()
            setOnSensorValuesChangedListener { values ->
//                Log.v("Sensors", "Light Sensor: ${values.joinToString()}")
                lux = values[0]
            }
        }

        accelerometer.apply {
            startListening()
            setOnSensorValuesChangedListener { values ->
//                Log.v("Sensors", "Acceleration Sensor: ${values.joinToString()}")
                acceleration = values
            }
        }
    }

    override fun onCleared() {
        lightSensor.stopListening()
        accelerometer.stopListening()
        super.onCleared()
    }
}
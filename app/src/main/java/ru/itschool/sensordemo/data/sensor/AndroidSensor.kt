package ru.itschool.sensordemo.data.sensor

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log

abstract class AndroidSensor(
    private val context: Context,
    private val sensorFeature: String,
    sensorType: Int
): MeasurableSensor(sensorType), SensorEventListener {

    final override val isSensorAvailable: Boolean
        get() = context.packageManager.hasSystemFeature(sensorFeature)


    private lateinit var sensorManager: SensorManager
    private var sensor: Sensor? = null

    override fun startListening() {
        if (!isSensorAvailable) {
            Log.e("Sensors", "$sensorFeature is not available")
            return
        }

        if (!::sensorManager.isInitialized && sensor == null) {
            sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
            sensor = sensorManager.getDefaultSensor(sensorType)
//            Log.v("Sensors", "Accessing sensor $sensor")
        }

        sensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
//            Log.v("Sensors", "Accessing sensor $it")
        }
    }

    override fun stopListening() {
        if (!isSensorAvailable || !::sensorManager.isInitialized) return

        sensorManager.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (!isSensorAvailable) return

        if (event?.sensor?.type == sensorType) {
            onSensorValuesChanged?.invoke(event.values.toList())
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) = Unit
}
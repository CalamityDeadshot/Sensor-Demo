package ru.itschool.sensordemo.data.sensor

abstract class MeasurableSensor(
    protected val sensorType: Int
) {

    protected var onSensorValuesChanged: ((List<Float>) -> Unit)? = null

    abstract val isSensorAvailable: Boolean

    abstract fun startListening()

    abstract fun stopListening()

    fun setOnSensorValuesChangedListener(listener: (List<Float>) -> Unit) {
        onSensorValuesChanged = listener
    }
}
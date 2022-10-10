package com.bjknrt.kotlin.data.vo

data class CalculationCycleResult(
    val start: java.time.LocalDateTime,
    val end: java.time.LocalDateTime,
    val between: kotlin.Long
) {

}
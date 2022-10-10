package com.bjknrt.kotlin.data.vo

/**
 * id: 主键
 * healthPlanId: 主表ID
 * explainId: 上级ID
 * frequencyTime 一周两次 中的 一
 * frequencyTimeUnit 一周两次 中的 周
 * frequencyNum 一周三次 中的 三
 * frequencyNumUnit 一周三次 中的 次
 */
class FrequencyParams(
    val id: java.math.BigInteger,
    val healthPlanId: java.math.BigInteger,
    val explainId: java.math.BigInteger?,
    val frequencyTime: Int,
    val frequencyTimeUnit: TimeUnit,
    val frequencyNum: Int,
    val frequencyNumUnit: FrequencyNumUnit
) {
}
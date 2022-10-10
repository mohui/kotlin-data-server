package com.bjknrt.kotlin.data.vo

import java.util.Objects
import com.fasterxml.jackson.annotation.JsonValue
import com.fasterxml.jackson.annotation.JsonProperty
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.Email
import javax.validation.constraints.Max
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern
import javax.validation.constraints.Size
import javax.validation.Valid

/**
* 健康计划类型
* Values: DRUG,BLOOD_PRESSURE_MEASUREMENT,SCIENCE_POPULARIZATION_PLAN,EXERCISE_PROGRAM,EXERCISE_PROGRAM_NOT_EVALUATED,EXERCISE_PROGRAM_ADJUSTMENT_REMIND,FASTING_BLOOD_GLUCOSE,MEAL_TWO_HOUR_RANDOM_BLOOD_GLUCOSE,DIET_PLAN,BEHAVIOR_VISIT,HYPERTENSION_VISIT,ONLINE_DIABETES,ONLINE_ACUTE_CORONARY_DISEASE,ONLINE_CEREBRAL_STROKE,ONLINE_COPD,OFFLINE_HYPERTENSION,OFFLINE_DIABETES,OFFLINE_ACUTE_CORONARY_DISEASE,OFFLINE_CEREBRAL_STROKE,OFFLINE_COPD
*/
enum class HealthPlanType(val value: kotlin.String) {

    /**
     * 药品
     */
    @JsonProperty("DRUG") DRUG("DRUG"),
    
    /**
     * 测血压
     */
    @JsonProperty("BLOOD_PRESSURE_MEASUREMENT") BLOOD_PRESSURE_MEASUREMENT("BLOOD_PRESSURE_MEASUREMENT"),
    
    /**
     * 科普计划
     */
    @JsonProperty("SCIENCE_POPULARIZATION_PLAN") SCIENCE_POPULARIZATION_PLAN("SCIENCE_POPULARIZATION_PLAN"),
    
    /**
     * 运动方案
     */
    @JsonProperty("EXERCISE_PROGRAM") EXERCISE_PROGRAM("EXERCISE_PROGRAM"),
    
    /**
     * 未进行评估(运动计划)
     */
    @JsonProperty("EXERCISE_PROGRAM_NOT_EVALUATED") EXERCISE_PROGRAM_NOT_EVALUATED("EXERCISE_PROGRAM_NOT_EVALUATED"),
    
    /**
     * 运动调整提醒(运动计划)
     */
    @JsonProperty("EXERCISE_PROGRAM_ADJUSTMENT_REMIND") EXERCISE_PROGRAM_ADJUSTMENT_REMIND("EXERCISE_PROGRAM_ADJUSTMENT_REMIND"),
    
    /**
     * 空腹血糖
     */
    @JsonProperty("FASTING_BLOOD_GLUCOSE") FASTING_BLOOD_GLUCOSE("FASTING_BLOOD_GLUCOSE"),
    
    /**
     * 餐后2h/随机血糖
     */
    @JsonProperty("MEAL_TWO_HOUR_RANDOM_BLOOD_GLUCOSE") MEAL_TWO_HOUR_RANDOM_BLOOD_GLUCOSE("MEAL_TWO_HOUR_RANDOM_BLOOD_GLUCOSE"),
    
    /**
     * 饮食计划
     */
    @JsonProperty("DIET_PLAN") DIET_PLAN("DIET_PLAN"),
    
    /**
     * 行为习惯随访(线上随访)
     */
    @JsonProperty("BEHAVIOR_VISIT") BEHAVIOR_VISIT("BEHAVIOR_VISIT"),
    
    /**
     * 高血压随访(线上随访)
     */
    @JsonProperty("HYPERTENSION_VISIT") HYPERTENSION_VISIT("HYPERTENSION_VISIT"),
    
    /**
     * 糖尿病(线上随访)
     */
    @JsonProperty("ONLINE_DIABETES") ONLINE_DIABETES("ONLINE_DIABETES"),
    
    /**
     * 冠心病(线上随访)
     */
    @JsonProperty("ONLINE_ACUTE_CORONARY_DISEASE") ONLINE_ACUTE_CORONARY_DISEASE("ONLINE_ACUTE_CORONARY_DISEASE"),
    
    /**
     * 脑卒中(线上随访)
     */
    @JsonProperty("ONLINE_CEREBRAL_STROKE") ONLINE_CEREBRAL_STROKE("ONLINE_CEREBRAL_STROKE"),
    
    /**
     * 慢阻肺(线上随访)
     */
    @JsonProperty("ONLINE_COPD") ONLINE_COPD("ONLINE_COPD"),
    
    /**
     * 高血压(线下随访)
     */
    @JsonProperty("OFFLINE_HYPERTENSION") OFFLINE_HYPERTENSION("OFFLINE_HYPERTENSION"),
    
    /**
     * 糖尿病(线下随访)
     */
    @JsonProperty("OFFLINE_DIABETES") OFFLINE_DIABETES("OFFLINE_DIABETES"),
    
    /**
     * 冠心病(线下随访)
     */
    @JsonProperty("OFFLINE_ACUTE_CORONARY_DISEASE") OFFLINE_ACUTE_CORONARY_DISEASE("OFFLINE_ACUTE_CORONARY_DISEASE"),
    
    /**
     * 脑卒中(线下随访)
     */
    @JsonProperty("OFFLINE_CEREBRAL_STROKE") OFFLINE_CEREBRAL_STROKE("OFFLINE_CEREBRAL_STROKE"),
    
    /**
     * 慢阻肺(线下随访)
     */
    @JsonProperty("OFFLINE_COPD") OFFLINE_COPD("OFFLINE_COPD")
    
}


package com.asetec.domain.dto.user

import kotlinx.serialization.Serializable

/**
 * 사용자에 대한 정보를 담는 데이터 클래스
 */
@Serializable
data class User(
    val id: String = "",
    val email: String = "",
    val name: String = "",

    val age: Float = 0f,
    val recentExerciseCheck: String = "네",
    val recentExerciseName: String? = null,

    val recentWalkingCheck: String = "네",
    val recentWalkingOfWeek: String = "",
    val recentWalkingOfTime: String = "",

    val targetPeriod: String = "네"
)
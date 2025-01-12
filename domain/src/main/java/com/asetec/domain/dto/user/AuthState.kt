package com.asetec.domain.dto.user

import kotlinx.serialization.Serializable

/**
 * 사용자에 대한 정보를 담는 데이터 클래스
 */
@Serializable
data class AuthState(
    val id: String = "",
    val email: String = "",
    val name: String = "",

    val age: Float = 0f,
    val recentExerciseName: String? = null,
    val recentExerciseCheck: String = "네",
    val recentWorkingJog: String = "네",
    val targetPeriod: String = "네"
)
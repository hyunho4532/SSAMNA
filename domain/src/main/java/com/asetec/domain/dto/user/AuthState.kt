package com.asetec.domain.dto.user

import kotlinx.serialization.Serializable

/**
 * 소셜 로그인을 한 후, 데이터를 저장하는 데이터 클래스
 */
@Serializable
data class AuthState(
    val id: String = "",
    val email: String = "",
    val name: String = ""
)
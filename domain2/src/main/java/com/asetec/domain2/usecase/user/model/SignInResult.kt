package com.asetec.domain2.usecase.user.model

data class SignInResult(
    val id: String,
    val email: String?,
    val displayName: String?
)
package com.asetec.domain2.usecase.user.repository

import com.asetec.domain2.usecase.user.model.SignInResult


interface AuthenticationRepository {
    suspend fun signInWithGoogle(): SignInResult
}
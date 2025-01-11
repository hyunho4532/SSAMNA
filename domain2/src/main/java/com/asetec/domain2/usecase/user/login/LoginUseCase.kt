package com.asetec.domain2.usecase.user.login

import com.asetec.domain2.usecase.user.model.SignInResult
import com.asetec.domain2.usecase.user.repository.AuthenticationRepository

class LoginUseCase(
    private val authenticationRepository: AuthenticationRepository
) {
    suspend operator fun invoke(): SignInResult {
        return authenticationRepository.signInWithGoogle()
    }
}
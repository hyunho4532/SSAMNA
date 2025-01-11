package com.asetec.domain.usecase.user

import com.asetec.domain.repository.user.AuthenticationRepository
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class LoginCase @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) {
    fun invoke(task: Task<GoogleSignInAccount>?, onSuccess: (id: String, email: String, name: String) -> Unit): Boolean {
        val authenticationRepository = authenticationRepository.signInWithGoogle(task) { id, email, name ->
            onSuccess(id, email, name)
        }

        return authenticationRepository
    }
}
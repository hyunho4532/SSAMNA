package com.asetec.data.supabase.repository

import com.asetec.domain.dto.user.User
import com.asetec.domain.dto.user.UserDTO
import com.asetec.domain.repository.user.AuthenticationRepository
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import io.github.jan.supabase.postgrest.Postgrest
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val postgrest: Postgrest
) : AuthenticationRepository {

    override fun signInWithGoogle(
        task: Task<GoogleSignInAccount>?,
        onSuccess: (id: String, email: String, name: String) -> Unit
    ): Boolean {

        val account = task?.getResult(ApiException::class.java)

        try {
            account?.let { signInAccount ->
                onSuccess(signInAccount.id.toString(), signInAccount.email.toString(), signInAccount.displayName.toString())
            } ?: run {
                onSuccess("", "", "")
            }
        } catch (e: ApiException) {
            onSuccess("", "", "")
        }

        return true
    }

    override suspend fun saveUser(user: User) {
        val userDTO = UserDTO(
            googleId = user.id,
            email = user.email,
            name = user.name,
        )

        postgrest.from("User").insert(userDTO)
    }
}
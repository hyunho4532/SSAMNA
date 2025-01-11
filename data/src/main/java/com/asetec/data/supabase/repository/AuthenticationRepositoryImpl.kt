package com.asetec.data.supabase.repository

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.gotrue.providers.builtin.IDToken
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val auth: Auth
) : AuthenticationRepository {
    override fun signInWithGoogle(
        task: Task<GoogleSignInAccount>?,
        onSuccess: (id: String) -> Unit
    ): Boolean {
        try {
            val account = task?.getResult(ApiException::class.java)

            account?.let { signInAccount ->
                onSuccess(signInAccount.id.toString())
            } ?: run {
                onSuccess("")
            }
        } catch (e: ApiException) {
            onSuccess("")
        }

        return true
    }
}
package com.asetec.data.supabase.repository

import android.content.Context
import android.content.Intent
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.providers.Google
import io.github.jan.supabase.gotrue.providers.builtin.IDToken
import javax.inject.Inject

@Suppress("UNUSED_EXPRESSION")
class AuthenticationRepositoryImpl @Inject constructor(
    private val auth: Auth
) : AuthenticationRepository {
    override suspend fun signInWithGoogle(idToken: String, nonce: String): Boolean {
        return try {
            auth.signInWith(IDToken) {
                idToken
                provider = Google
                nonce
            }
            true
        } catch (e: Exception) {
            false
        }
    }
}
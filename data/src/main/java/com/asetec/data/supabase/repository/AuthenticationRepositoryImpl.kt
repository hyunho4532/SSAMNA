package com.asetec.data.supabase.repository

import android.content.Context
import android.content.Intent
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.providers.Google
import javax.inject.Inject

class AuthenticationRepositoryImpl @Inject constructor(
    private val auth: Auth
) : AuthenticationRepository {
    override suspend fun signInWithGoogle(): Boolean {
        return try {
            auth.signInWith(Google)

            true
        } catch (e: Exception) {
            false
        }
    }
}
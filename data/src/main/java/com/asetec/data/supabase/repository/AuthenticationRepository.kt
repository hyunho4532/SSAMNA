package com.asetec.data.supabase.repository


interface AuthenticationRepository {
    suspend fun signInWithGoogle(idToken: String, nonce: String): Boolean
}
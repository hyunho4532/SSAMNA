package com.asetec.data.supabase.repository


interface AuthenticationRepository {
    suspend fun signInWithGoogle(): Boolean
}
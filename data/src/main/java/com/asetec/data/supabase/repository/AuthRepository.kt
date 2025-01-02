package com.asetec.data.supabase.repository

interface AuthRepository {
    suspend fun signInWithGoogle(): Boolean
}
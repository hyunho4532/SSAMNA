package com.asetec.data.supabase.repository

import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task


interface AuthenticationRepository {
    fun signInWithGoogle(task: Task<GoogleSignInAccount>?, onSuccess: (id: String) -> Unit): Boolean
}
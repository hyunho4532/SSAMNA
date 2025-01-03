package com.asetec.presentation.viewmodel

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asetec.data.supabase.repository.AuthenticationRepository
import com.asetec.presentation.ui.main.HomeActivity
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {

    fun onGoogleSignIn(task: Task<GoogleSignInAccount>?) {
        try {
            val account = task?.getResult(ApiException::class.java)
            account?.let {
                Log.d("SignInViewModel", "Google sign in success: ${account.id}")
            } ?: run {
                Log.d("SignInViewModel", "로그인 실패")
            }
        } catch (e: ApiException) {
            Log.e("LoginViewModel", "Google sign in failed: ${e.statusCode}")
        }
    }
}
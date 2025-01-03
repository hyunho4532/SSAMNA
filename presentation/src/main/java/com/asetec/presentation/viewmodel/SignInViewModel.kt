package com.asetec.presentation.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asetec.data.supabase.repository.AuthenticationRepository
import com.asetec.presentation.ui.main.HomeActivity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {
    fun onGoogleSignIn() {
        viewModelScope.launch {
            authenticationRepository.signInWithGoogle()
        }
    }
}
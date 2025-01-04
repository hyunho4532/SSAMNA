package com.asetec.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asetec.data.supabase.repository.AuthenticationRepository
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.SignInAccount
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository,
    @ApplicationContext private val appContext: Context
) : ViewModel() {

    private val sharedPreferences = appContext.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    /** 아이디 **/
    private val _id = MutableStateFlow(getSavedLoginState())
    val id: Flow<String> = _id

    /** 이메일 **/
    private val _email = MutableStateFlow("")
    val email: Flow<String> = _email

    /** 이름 (별칭) **/
    private val _name = MutableStateFlow("")
    val name: Flow<String> = _name

    private fun getSavedLoginState(): String {
        return sharedPreferences.getString("id", "")!!
    }

    private fun saveLoginState(id: String) {
        sharedPreferences.edit().putString("id", id).apply()
    }

    fun onGoogleSignIn(task: Task<GoogleSignInAccount>?) {
        viewModelScope.launch {
            authenticationRepository.signInWithGoogle(task) { id ->
                _id.value = id
                saveLoginState(id)
            }
        }
    }
}
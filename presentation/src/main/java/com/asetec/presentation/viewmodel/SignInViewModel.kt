package com.asetec.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asetec.domain.dto.user.AuthState
import com.asetec.domain.usecase.user.LoginCase
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val loginCase: LoginCase,
    @ApplicationContext private val appContext: Context
) : ViewModel() {

    private val sharedPreferences = appContext.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    private val _authState = MutableStateFlow(AuthState(
        id = getSavedLoginState()
    ))

    val authState: StateFlow<AuthState> = _authState

    /**
     * SP에 담은 id 값을 가져온다.
     */
    private fun getSavedLoginState(): String {
        return sharedPreferences.getString("id", "")!!
    }

    /**
     * 구글 로그인 진행 후, id 값을 SP에 담는다.
     */
    private fun saveLoginState(id: String) {
        sharedPreferences.edit().putString("id", id).apply()
    }

    fun onGoogleSignIn(task: Task<GoogleSignInAccount>?, onSuccess: (Boolean) -> Unit) {
        viewModelScope.launch {
            loginCase.invoke(task) { id, email, name ->
                saveLoginState(id)

                _authState.update {
                    it.copy(
                        id = id,
                        email = email,
                        name = name
                    )
                }


                onSuccess(true)
            }
        }
    }
}
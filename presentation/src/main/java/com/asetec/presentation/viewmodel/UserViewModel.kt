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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val loginCase: LoginCase,
    @ApplicationContext private val appContext: Context
) : ViewModel() {

    private val sharedPreferences = appContext.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    private val _authState = MutableStateFlow(AuthState(
        id = getSavedLoginState(),
        recentExerciseName = ""
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

    fun mergeAuthStateIntoUserState(authState: AuthState): Boolean {
        _authState.update {
            it.copy(
                id = authState.id,
                email = authState.email,
                name = authState.name
            )
        }

        return true
    }

    fun onGoogleSignIn(task: Task<GoogleSignInAccount>?) {
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
            }
        }
    }

    /**
     * NumberPicker에서 값이 변경될 때마다, 나이를 저장한다.
     */
    fun saveAge(age: Float) {
        _authState.update {
            it.copy(age = age)
        }
    }

    fun saveExerciseName(name: String) {
        _authState.update {
            it.copy(recentExerciseName = name)
        }
    }

    fun saveChecks(id: Number, text: String) {
        _authState.update {
            when (id) {
                0 -> it.copy(recentExerciseCheck = text)
                1 -> it.copy(recentWorkingJog = text)
                2 -> it.copy(targetPeriod = text)
                else -> throw Exception("에러")
            }
        }
    }
}
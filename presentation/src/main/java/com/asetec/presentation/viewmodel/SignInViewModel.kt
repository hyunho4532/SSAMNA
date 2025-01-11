package com.asetec.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.asetec.domain.usecase.user.LoginCase
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val loginCase: LoginCase,
    @ApplicationContext private val appContext: Context
) : ViewModel() {

    private val sharedPreferences = appContext.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    /** 아이디 **/
    private val _id = MutableStateFlow(getSavedLoginState())
    val id: Flow<String> = _id

    /** 나이 **/
    private val _age = MutableStateFlow(0f)
    val age: Flow<Float> = _age

    /** 이메일 **/
    private val _email = MutableStateFlow("")
    val email: Flow<String> = _email

    /** 이름 (별칭) **/
    private val _name = MutableStateFlow("")
    val name: Flow<String> = _name

    /** 최근 운동 이름 **/
    private val _recentExercise = MutableStateFlow("")
    val recentExercise: Flow<String> = _recentExercise

    /**
     * NumberPicker에서 값이 변경될 때마다, 나이를 저장한다.
     */
    fun saveAge(age: Float) {
        _age.value = age
    }

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
            loginCase.invoke(task) { id ->
                _id.value = id
                saveLoginState(id)

                onSuccess(true)
            }
        }
    }
}
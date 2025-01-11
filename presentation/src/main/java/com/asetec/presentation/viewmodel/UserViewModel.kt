package com.asetec.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.asetec.domain.dto.user.UserState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(

) : ViewModel() {

    /** 사용자에 관한 모든 것 **/
    private val _userState = MutableStateFlow(UserState())
    val userState: StateFlow<UserState> = _userState

    /** 나이 **/
    private val _age = MutableStateFlow(0f)
    val age: StateFlow<Float> = _age

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
            _userState.update { currentAge ->
                currentAge.copy(age = age)
            }

            Log.d("UserViewModel: ", _userState.value.age.toString())
        }
}
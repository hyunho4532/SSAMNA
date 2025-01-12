package com.asetec.presentation.ui.login

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.asetec.domain.dto.user.AuthState
import com.asetec.presentation.component.RadioRow
import com.asetec.presentation.ui.tool.Spacer
import com.asetec.presentation.viewmodel.SignInViewModel
import com.asetec.presentation.viewmodel.UserViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun UserInfoScreen(
    navController: NavController,
    authState: AuthState,
    userViewModel: UserViewModel = hiltViewModel()
) {

    val yesORNo = listOf("네", "아니요")

    val userState = userViewModel.userState.collectAsState()

    var recentExercise by rememberSaveable {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
        ) {
            Text(
                text = "회원님의 정보가 필요해요!",
                modifier = Modifier.padding(top = 16.dp, start = 16.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "정보에 맞게 운동 정보를 제공해드립니다!",
                modifier = Modifier.padding(top = 48.dp, start = 16.dp),
                fontSize = 16.sp
            )
        }

        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
        ) {
            Row {
                Text(
                    text = "1. 나이를 선택해주세요!",
                    modifier = Modifier.padding(top = 36.dp, start = 16.dp),
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = "회원님의 나이: ${userState.value.age.toInt()}살",
                    modifier = Modifier.padding(top = 36.dp, start = 12.dp)
                )
            }

            Slider(
                modifier = Modifier
                    .width(370.dp)
                    .padding(top = 52.dp, start = 16.dp),
                value = userState.value.age,
                onValueChange = {
                    userViewModel.saveAge(it)
                },
                colors = SliderDefaults.colors(
                    thumbColor = Color(0xFF42B4F5),
                    activeTrackColor = Color(0xFF156ffa),
                    inactiveTrackColor = Color(0xFF5898fa)
                ),
                steps = 99,
                valueRange = 0f..100f
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(top = 24.dp)
        ) {
            Row {
                Text(
                    text = "2. 최근 운동을 하신 적이 있으신가요?",
                    modifier = Modifier.padding(start = 16.dp),
                    fontWeight = FontWeight.Bold
                )
            }

            RadioRow(yesORNo = yesORNo)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(130.dp)
                .padding(top = 24.dp)
        ) {
            Text(
                text = "2-1. 최근 운동을 진행하셨다면 어떤 운동을 하셨나요?",
                modifier = Modifier.padding(start = 16.dp),
                fontWeight = FontWeight.Bold
            )

            Box(
                modifier = Modifier.padding(top = 36.dp, start = 16.dp)
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .width(240.dp)
                        .height(48.dp),
                    value = recentExercise,
                    onValueChange = {
                        recentExercise = it
                    }
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(110.dp)
                .padding(top = 24.dp)
        ) {
            Text(
                text = "3. 하루에 꾸준히 걷기 또는 달리기를 하시나요?",
                modifier = Modifier.padding(start = 16.dp),
                fontWeight = FontWeight.Bold
            )

            RadioRow(yesORNo = yesORNo)
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(top = 24.dp)
        ) {
            Text(
                text = "4. 운동 중 목표 기간이 있습니까?",
                modifier = Modifier.padding(start = 16.dp),
                fontWeight = FontWeight.Bold
            )

            RadioRow(yesORNo = yesORNo)
        }
        
        Button(
            onClick = {
                val userStateJson = Uri.encode(Json.encodeToString(userState.value))
                val authStateJson = Uri.encode(Json.encodeToString(authState))

                navController.navigate("report?userState=${userStateJson}&authState=${authStateJson}")
            },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF5c9afa)
            )
        ) {
            Text(text = "정보 작성 완료!")
        }
    }
}
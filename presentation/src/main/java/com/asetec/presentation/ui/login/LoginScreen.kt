package com.asetec.presentation.ui.login

import android.net.Uri
import android.util.Base64
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.asetec.presentation.R
import com.asetec.presentation.api.GoogleApiContract
import com.asetec.presentation.ui.tool.CustomCard
import com.asetec.presentation.ui.tool.Spacer
import com.asetec.presentation.viewmodel.SignInViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun LoginScreen(
    viewModel: SignInViewModel = hiltViewModel(),
    navController: NavController
) {

    val authState by viewModel.authState.collectAsState()

    val authResultLauncher = rememberLauncherForActivityResult (
        contract = GoogleApiContract()
    ) { task ->
        viewModel.onGoogleSignIn(task)
    }
    
    LaunchedEffect(key1 = authState.email) {
        if (authState.email.isNotEmpty()) {
            val authStateJson = Uri.encode(Json.encodeToString(authState))
            navController.navigate("userInfo?authState=${authStateJson}")
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Spacer(width = 0.dp, height = 120.dp)

        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(460.dp)
        ) {
            Text(
                text = "나 자신을 관리하자.",
                modifier = Modifier.padding(start = 16.dp),
                fontSize = 26.sp,
            )

            Text(
                text = "땀나(SSAMNA) 💦",
                modifier = Modifier.padding(start = 16.dp, top = 34.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "환영합니다! 같이 떠나볼까요?",
                modifier = Modifier.padding(start = 16.dp, top = 66.dp),
                fontSize = 14.sp,
                color = Color.Gray
            )
        }

        Box (
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clickable {
                    authResultLauncher.launch(1)
                }
        ) {
            CustomCard(
                width = 300.dp, height = 52.dp,
                text = "구글 계정으로 로그인",
                id = R.drawable.google
            )
        }

        Spacer(width = 0.dp, height = 16.dp)

        Box (
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            CustomCard(
                width = 300.dp, height = 52.dp,
                text = "카카오 계정으로 로그인",
                id = R.drawable.kakao
            )
        }
    }
}
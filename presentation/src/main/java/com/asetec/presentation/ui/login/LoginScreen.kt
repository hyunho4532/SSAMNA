package com.asetec.presentation.ui.login

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.asetec.presentation.R
import com.asetec.presentation.api.GoogleApiContract
import com.asetec.presentation.ui.responsive.setTopPadding
import com.asetec.presentation.ui.tool.CustomCard
import com.asetec.presentation.ui.tool.Spacer
import com.asetec.presentation.viewmodel.UserViewModel
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Composable
fun LoginScreen(
    viewModel: UserViewModel = hiltViewModel(),
    navController: NavController
) {
    val authState by viewModel.user.collectAsState()

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

    CompositionLocalProvider(
        LocalDensity provides Density(
            density = LocalDensity.current.density,
            fontScale = 1f
        )
    ) {
        BoxWithConstraints (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            val screenWidth = maxWidth
            val screenHeight = maxHeight

            val topPadding = setTopPadding(screenWidth)

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        top = topPadding,
                        start = 8.dp
                    )
            ) {
                Box (
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(screenHeight * 0.50f)
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
                        width = screenWidth * 0.8f,
                        height = 52.dp,
                        text = "구글 계정으로 로그인",
                        id = R.drawable.google
                    )
                }

                Spacer(width = 0.dp, height = 16.dp)

                Box (
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    CustomCard(
                        width = screenWidth * 0.8f,
                        height = 52.dp,
                        text = "카카오 계정으로 로그인",
                        id = R.drawable.kakao
                    )
                }
            }
        }
    }
}
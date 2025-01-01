package com.asetec.presentation.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asetec.presentation.R
import com.asetec.presentation.animation.SplashLoader
import com.asetec.presentation.ui.tool.Spacer

@Composable
fun OnBoardingScreen() {
    OnBoardingPreView()
}

@Preview
@Composable
fun OnBoardingPreView() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box (
            modifier = Modifier.padding(top = 16.dp, start = 16.dp)) {
            Text(
                text = "운동할 땐 땀💦 배출하자!",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Box (
            modifier = Modifier.padding(top = 4.dp, start = 16.dp)
        ) {
            Text(
                text = "앱을 이용해주셔서 감사합니다."
            )
        }

        Spacer(width = 0.dp, height = 120.dp)

        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
        ) {
            SplashLoader(R.raw.init)
        }
    }
}
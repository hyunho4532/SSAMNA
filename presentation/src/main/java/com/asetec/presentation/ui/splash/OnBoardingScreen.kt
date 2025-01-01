package com.asetec.presentation.ui.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.asetec.presentation.R
import com.asetec.presentation.animation.SplashLoader
import com.asetec.presentation.ui.tool.CustomButton
import com.asetec.presentation.ui.tool.Spacer

@Composable
fun OnBoardingScreen(navController: NavController) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {

        Spacer(width = 0.dp, height = 120.dp)

        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
            ) {
                SplashLoader(R.raw.init)
            }

            Text(
                text = "운동할 땐 땀💦 배출하자!",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold,
            )

            Text(
                text = "언제 어디서든 편하게!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )

            Text(
                text = "운동을 즐기세요",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(width = 0.dp, height = 140.dp)

            CustomButton(
                width = 320.dp,
                height = 46.dp,
                navController = navController
            )
        }
    }
}
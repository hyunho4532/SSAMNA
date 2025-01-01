package com.asetec.presentation.ui.splash

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun OnBoardingScreen() {

}

@Preview
@Composable
fun OnBoardingPreView() {
    Column (
        modifier = Modifier.width()
    ) {
        Text(text = "환영합니다!")
    }
}
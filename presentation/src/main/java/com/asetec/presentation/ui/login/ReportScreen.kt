package com.asetec.presentation.ui.login

import android.util.Log
import androidx.compose.foundation.background
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asetec.domain.dto.user.AuthState
import com.asetec.domain.dto.user.UserState
import com.asetec.presentation.ui.tool.ReportCard

/** 정보 수집 후 사용자에 관한 최종 정보 **/
@Composable
fun ReportScreen(
    userState: UserState,
    authState: AuthState
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box (
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
        ) {
            Text(
                text = "회원님의 정보를 수집했어요!",
                modifier = Modifier.padding(top = 16.dp, start = 16.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Text(
                text = "정보를 수정하고 싶으시면 뒤로 이동해주세요!",
                modifier = Modifier.padding(top = 48.dp, start = 16.dp),
                fontSize = 16.sp
            )
        }

        Box (
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            ReportCard(
                width = 360.dp,
                height = 540.dp,
                userState = userState,
                authState = authState
            )
        }
    }
}
package com.asetec.presentation.ui.login

import android.util.Log
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asetec.domain.dto.user.UserState
import com.asetec.presentation.R
import com.asetec.presentation.ui.tool.CustomCard
import com.asetec.presentation.ui.tool.ReportCard
import com.asetec.presentation.viewmodel.UserViewModel

/** 정보 수집 후 사용자에 관한 최종 정보 **/
@Composable
fun ReportScreen(userState: UserState) {

    Log.d("ReportScreen", userState.age.toString())

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
                width = 360.dp, height = 540.dp,
                text = "구글 계정으로 로그인"
            )
        }
    }
}
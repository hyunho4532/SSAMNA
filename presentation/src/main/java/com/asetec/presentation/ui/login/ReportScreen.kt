package com.asetec.presentation.ui.login

import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asetec.domain.dto.user.User
import com.asetec.presentation.ui.main.home.HomeActivity
import com.asetec.presentation.ui.tool.ReportCard
import com.asetec.presentation.viewmodel.UserViewModel

/** 정보 수집 후 사용자에 관한 최종 정보 **/
@Composable
fun ReportScreen(
    userState: User,
    userViewModel: UserViewModel = hiltViewModel()
) {

    val context = LocalContext.current

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
                userState = userState
            )
        }

        Button(
            onClick = {
                userViewModel.saveUser(userState)

                val intent = Intent(context, HomeActivity::class.java)
                context.startActivity(intent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 14.dp),
            shape = RectangleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF5c9afa)
            )
        ) {
            Text(text = "작성 확인!")
        }
    }
}
package com.asetec.presentation.ui.tool

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.asetec.domain.dto.user.User

@Composable
fun CustomCard(width: Dp, height: Dp, text: String, id: Int) {
    Card (
        modifier = Modifier
            .width(width)
            .height(height)
            .shadow(
                elevation = 3.dp
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentAlignment = Alignment.Center
        ) {
            Row (
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Icon(
                    painter = painterResource(id = id),
                    contentDescription = "로고",
                    tint = Color.Unspecified
                )
                Text(
                    text = text,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(start = 6.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    }
}

@Composable
fun ReportCard(
    width: Dp,
    height: Dp,
    userState: User
) {

    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImageUri = uri
        }
    )

    Card (
        modifier = Modifier
            .width(width)
            .height(height)
            .shadow(
                elevation = 3.dp
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.padding(top = 24.dp)
            ) {
                AsyncImage(
                    model = selectedImageUri,
                    contentDescription = "avatar",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.Gray, CircleShape)
                        .clickable {
                            singlePhotoPickerLauncher.launch(
                                PickVisualMediaRequest(
                                    ActivityResultContracts.PickVisualMedia.ImageOnly
                                )
                            )
                        }
                )
            }

            Text(
                text = "${userState.name} : ${userState.age.toInt()}살",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 6.dp)
            )

            Text(
                text = userState.email,
                fontSize = 16.sp,
            )

            Spacer(width = 0.dp, height = 80.dp)

            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Box(
                    modifier = Modifier
                        .height(80.dp)
                ) {
                    Text(
                        text = "최근 운동을 진행한 적이 있으신가요? ${userState.recentExerciseCheck}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    Text(
                        text = "최근 진행하고 있는 운동: ${userState.recentExerciseName}",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 24.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .height(80.dp)
                ) {
                    Text(
                        text = "하루에 걷기 또는 달리기를 하시나요? ${userState.recentWalkingCheck}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )

                    Text(
                        text = "주: ${userState.recentWalkingOfWeek}회 ${userState.recentWalkingOfTime}분씩 진행!",
                        fontSize = 14.sp,
                        modifier = Modifier.padding(top = 24.dp)
                    )
                }

                Box(
                    modifier = Modifier
                        .height(80.dp)
                ) {
                    Text(
                        text = "운동 중 목표 기간이 있습니까? ${userState.targetPeriod}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}
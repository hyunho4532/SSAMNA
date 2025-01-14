package com.asetec.presentation.component

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asetec.presentation.R
import com.asetec.presentation.component.dialog.BottomSheet
import com.asetec.presentation.ui.tool.Spacer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAside(context: Context) {

    var showBottomSheet = remember {
        mutableStateOf(false)
    }

    val sheetState = rememberModalBottomSheetState(
        skipPartiallyExpanded = false
    )

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Row (
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceAround,
        ) {
            Column (
                modifier = Modifier
                    .clickable (
                        interactionSource = remember {
                            MutableInteractionSource()
                        },
                        indication = rememberRipple(
                            color = Color.Gray,
                            bounded = false
                        )
                    ) {
                        showBottomSheet.value = true
                    }
            ) {
                Text(
                    text = "활동 종류",
                    modifier = Modifier.padding(
                        top = 8.dp,
                    )
                )

                Row (
                    modifier = Modifier
                        .padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Image(
                        modifier = Modifier.size(20.dp),
                        painter = painterResource(id = R.drawable.baseline_persons_run_24),
                        contentDescription = "달리는 사람 아이콘"
                    )

                    Text(
                        text = "달리기",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Column(
                modifier = Modifier.clickable(
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    indication = rememberRipple(
                        color = Color.Gray,
                        bounded = false
                    )
                ) {

                }
            ) {
                Text(
                    text = "운동 시간",
                    modifier = Modifier.padding(top = 8.dp)
                )

                Row (
                    modifier = Modifier
                        .padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Image(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.baseline_access_time_24),
                        contentDescription = "운동 시간 아이콘"
                    )

                    Spacer(width = 2.dp, height = 0.dp)

                    Text(
                        text = "시간",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Column(
                modifier = Modifier.clickable(
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    indication = rememberRipple(
                        color = Color.Gray,
                        bounded = false
                    )
                ) {

                }
            ) {
                Text(
                    text = "목표 거리",
                    modifier = Modifier.padding(
                        top = 8.dp,
                    )
                )

                Row (
                    modifier = Modifier
                        .padding(top = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {

                    Image(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(id = R.drawable.baseline_fmd_good_24),
                        contentDescription = "목표 지점 아이콘"
                    )

                    Spacer(width = 2.dp, height = 0.dp)

                    Text(
                        text = "거리",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }

        BottomSheet(
            sheetState = sheetState,
            showBottomSheet = showBottomSheet
        )
    }
}
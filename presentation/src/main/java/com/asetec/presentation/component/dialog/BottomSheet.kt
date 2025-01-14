package com.asetec.presentation.component.dialog

import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.asetec.presentation.ui.tool.activateCard
import com.asetec.presentation.viewmodel.JsonParseViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheet(
    context: Context,
    showBottomSheet: MutableState<Boolean>,
    sheetState: SheetState,
    jsonParseViewModel: JsonParseViewModel = hiltViewModel()
) {

    var dataIsLoading by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(key1 = Unit) {
        if (jsonParseViewModel.activateJsonData.isEmpty()) {
            jsonParseViewModel.activateJsonParse()
        }
        dataIsLoading = true
    }

    if (showBottomSheet.value) {
        if (dataIsLoading) {
            ModalBottomSheet(
                modifier = Modifier
                    .fillMaxSize(),
                sheetState = sheetState,
                onDismissRequest = { showBottomSheet.value = false },
                containerColor = Color.White
            ) {

                Box(
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text(
                        "활동 종류를 선택해주세요!",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                Column (
                    modifier = Modifier.fillMaxSize().padding(top = 16.dp),
                    verticalArrangement = Arrangement.Top,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    jsonParseViewModel.activateJsonData.forEach { activate ->
                        activateCard(
                            context = context,
                            width = 340.dp,
                            height = 60.dp,
                            activate = activate,
                            showBottomSheet = showBottomSheet
                        )
                    }
                }
            }
        } else {
            Text(text = "데이터를 불러오고 있습니다!")
        }
    }
}
package com.asetec.presentation.ui.tool

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.asetec.presentation.R

@Composable
fun CustomButton(
    width: Dp,
    height: Dp,
    text: String,
    showIcon: Boolean,
    backgroundColor: Color,
    navController: NavController?
) {
    Button(
        onClick = {
            navController?.navigate("login") {
                popUpTo("splash") {
                    inclusive = true
                }
            }
        },
        modifier = Modifier
            .wrapContentSize()
            .width(width)
            .height(height),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor
        )
    ) {

        if (showIcon) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_water_drop_24),
                contentDescription = "운동 여정하기!"
            )

            Spacer(width = 8.dp, height = 0.dp)
        }

        Text(text = text)
    }
}
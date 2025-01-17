package com.asetec.presentation.component

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.PratikFagadiya.smoothanimationbottombar.model.SmoothAnimationBottomBarScreens
import com.PratikFagadiya.smoothanimationbottombar.properties.BottomBarProperties
import com.PratikFagadiya.smoothanimationbottombar.ui.SmoothAnimationBottomBar
import com.asetec.presentation.ui.theme.Blue
import com.asetec.presentation.ui.theme.BlueTint

@Composable
fun BottomNavigationBar(
    items: List<SmoothAnimationBottomBarScreens>,
    currentIndex: MutableIntState,
    navController: NavHostController
) {
    SmoothAnimationBottomBar (navController = navController,
        bottomNavigationItems = items,
        initialIndex = currentIndex,
        bottomBarProperties = BottomBarProperties(
            backgroundColor = Color.White,
            indicatorColor = Color.White.copy(alpha = 0.5F),
            iconTintColor = Color.Gray,
            iconTintActiveColor = Color.Black,
            textActiveColor = Color.Black,
            cornerRadius = 18.dp,
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp
        ),
        onSelectItem = {
            Log.d("BottomNavigationBar", it.name)
        }
    )
}
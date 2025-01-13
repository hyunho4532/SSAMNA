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
            backgroundColor = Blue,     // Change the background color
            indicatorColor = Color.White.copy(alpha = 0.2F),  // Change the indicator color with Alpha
            iconTintColor = BlueTint, // Change the icon tint color
            iconTintActiveColor = Color.White, // Change the active icon tint color
            textActiveColor = Color.White, // Change the active text color
            cornerRadius = 18.dp,  // Increase the corner radius
            fontWeight = FontWeight.Medium,  // Change the font weight
            fontSize = 16.sp // Increase or Decrease the font size
        ),
        onSelectItem = {
            Log.d("BottomNavigationBar", it.name)
        }
    )
}
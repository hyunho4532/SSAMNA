package com.asetec.presentation.component

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.asetec.presentation.ui.main.home.screen.HomeScreen
import com.asetec.presentation.ui.main.home.screen.ProfileScreen

@Composable
fun BottomNavigationBar() {
    var selectedIndex by remember {
        mutableIntStateOf(0)
    }

    val items = listOf("메인", "프로필")

    Scaffold (
        bottomBar = {
            NavigationBar (
                containerColor = Color.White,
                modifier = Modifier.height(56.dp)
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        icon = {
                           when (item) {
                               "메인" -> Icon(
                                   Icons.Default.Home,
                                   contentDescription = item
                               )
                               "프로필" -> Icon(
                                   Icons.Default.Person,
                                   contentDescription = item
                               )
                           }
                        },
                        label = {
                            Text(
                                text = item,
                                fontSize = 12.sp
                            )
                        },
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color.Black,
                            unselectedIconColor = Color.Gray
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        Log.d("BottomNavigationBar", innerPadding.toString())

        Box(modifier = Modifier.fillMaxSize()) {
            when (selectedIndex) {
                0 -> HomeScreen()
                1 -> ProfileScreen()
            }
        }
    }
}
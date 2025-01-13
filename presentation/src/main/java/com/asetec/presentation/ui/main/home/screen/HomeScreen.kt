package com.asetec.presentation.ui.main.home.screen

import androidx.compose.runtime.Composable
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState


@Composable
fun HomeScreen() {
    val initialPosition = LatLng(37.5665, 126.9780)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initialPosition, 12f)
    }

    GoogleMap(
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = MarkerState(position = initialPosition),
            title = "서울",
            snippet = "한국의 수도"
        )
    }
}
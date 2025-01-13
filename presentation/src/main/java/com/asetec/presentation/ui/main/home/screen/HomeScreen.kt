package com.asetec.presentation.ui.main.home.screen

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.asetec.presentation.service.LocationService
import com.asetec.presentation.ui.tool.CircularProgress
import com.asetec.presentation.viewmodel.LocationViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import dagger.hilt.android.qualifiers.ApplicationContext

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun HomeScreen(
    fusedLocationClient: FusedLocationProviderClient,
    locationViewModel: LocationViewModel = hiltViewModel(),
    @ApplicationContext context: Context
) {

    val locationState = locationViewModel.location.collectAsState()

    val locationService = LocationService()

    var isLocationLoaded by remember {
        mutableStateOf(false)
    }

    val locationPermissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    )

    LaunchedEffect (key1 = Unit) {
        locationPermissionState.launchMultiplePermissionRequest()
    }

    LaunchedEffect(locationPermissionState.allPermissionsGranted) {
        if (locationPermissionState.allPermissionsGranted) {
            locationViewModel.getCurrentLocation(fusedLocationClient) {
                isLocationLoaded = true
            }
        }
    }

    val cameraPositionState = rememberCameraPositionState()

    LaunchedEffect(locationState.value.latitude, locationState.value.latitude) {
        if (isLocationLoaded) {
            cameraPositionState.move(
                CameraUpdateFactory.newLatLngZoom(LatLng(locationState.value.latitude, locationState.value.longitude), 12f)
            )
        }
    }

    if (locationPermissionState.allPermissionsGranted) {
        if (isLocationLoaded) {
            GoogleMap(
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = MarkerState(position = LatLng(locationState.value.latitude, locationState.value.longitude)),
                    title = "서울",
                    snippet = "한국의 수도"
                )
            }

            Button(onClick = {
                locationViewModel.startLocationUpdates(context)
            }) {
                Text(text = "테스트")
            }
            
        } else {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgress(text = "현재 위치를 불러오고 있습니다!")
            }
            
        }
    }
}
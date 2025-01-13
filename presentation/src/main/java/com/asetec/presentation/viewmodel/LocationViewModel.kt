package com.asetec.presentation.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asetec.domain.model.location.Location
import com.asetec.presentation.service.LocationService
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val locationService: LocationService
): ViewModel() {

    private val _locationLiveData = MutableLiveData<android.location.Location>()

    val locationLiveData: LiveData<android.location.Location> = _locationLiveData

    private val _location = MutableStateFlow(Location(
        latitude = 0.0,
        longitude = 0.0
    ))


    val location: StateFlow<Location> = _location

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(
        fusedLocationClient: FusedLocationProviderClient,
        isLocationLoaded: (Boolean) -> Unit
    ) {
        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            if (location != null) {
                _location.value = Location(
                    latitude = location.latitude,
                    longitude = location.longitude
                )

                isLocationLoaded(true)
            } else {
                isLocationLoaded(false)
            }
        }
    }

    fun startLocationUpdates(context: Context) {
        locationService.scheduleJob(context = context)
    }
}
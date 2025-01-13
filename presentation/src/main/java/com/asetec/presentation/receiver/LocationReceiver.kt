package com.asetec.presentation.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.location.Location
import android.util.Log

class LocationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val location = intent.getParcelableExtra<Location>("location")

        if (location != null) {
            Log.d("LocationReceiver", "위치: ${location.latitude
            }, ${location.longitude}")
        }
    }
}
package com.agrriguard.utils

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import com.google.android.gms.location.LocationServices
import com.google.android.gms.tasks.Task
import kotlin.math.*

object GpsUtils {

    @SuppressLint("MissingPermission")
    fun getCurrentLocation(context: Context): Task<Location> {
        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        return fusedLocationClient.lastLocation
    }

    /**
     * Calcule la distance entre deux points en kilomètres en utilisant la formule de Haversine.
     */
    fun calculateDistance(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
        val r = 6371 // Rayon de la Terre en km
        val dLat = Math.toRadians(lat2 - lat1)
        val dLon = Math.toRadians(lon2 - lon1)
        val a = sin(dLat / 2).pow(2) +
                cos(Math.toRadians(lat1)) * cos(Math.toRadians(lat2)) *
                sin(dLon / 2).pow(2)
        val c = 2 * atan2(sqrt(a), sqrt(1 - a))
        return r * c
    }
}

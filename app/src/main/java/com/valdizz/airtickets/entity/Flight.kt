package com.valdizz.airtickets.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Data class with flight information.
 *
 * @author Vlad Kornev
 */
@Parcelize
data class Flight(
    val flightType: FlightType,
    val date: String,
    val price: String,
    val freeSeats: String,
    val fromAirport: String,
    val toAirport: String,
    val timeTakeoff: String,
    val timeLand: String,
    val timeFlight: String
) : Parcelable
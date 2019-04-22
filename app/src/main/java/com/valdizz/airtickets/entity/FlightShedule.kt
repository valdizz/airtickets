package com.valdizz.airtickets.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Data class with information about flight schedule.
 *
 * @author Vlad Kornev
 */
@Parcelize
data class FlightShedule(val departFlight: Flight, val returnFlight: Flight) : Parcelable


package com.valdizz.airtickets.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FlightShedule(val departFlight: Flight, val returnFlight: Flight) : Parcelable


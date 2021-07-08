package com.example.investors.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User (
    val id :String  = "",
    val firstname : String = "",
    val surname : String = "",
    val email : String = "",
    val mobile : Long = 0,
    val profileCompleted :Int = 0
):Parcelable
package com.rendi.foodorderapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Catalog(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val price: Double,
    val imagePictUrl: String,
    val description: String,
    val location: String,
    val urlLocation: String
) : Parcelable
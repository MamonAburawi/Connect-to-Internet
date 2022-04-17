package com.example.connecttointernet.model


import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

data class Photo(
    @SerializedName("camera")
    val camera: Camera? = null,
    @SerializedName("earth_date")
    val earthDate: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @Json(name = "img_src")
    val imgSrc: String = "",
    @SerializedName("rover")
    val rover: Rover? = null,
    @SerializedName("sol")
    val sol: Int = 0
)
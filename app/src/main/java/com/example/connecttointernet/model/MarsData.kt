package com.example.connecttointernet.model


import com.google.gson.annotations.SerializedName

data class MarsData(
    @SerializedName("photos")
    val photos: List<Photo> = ArrayList()
)
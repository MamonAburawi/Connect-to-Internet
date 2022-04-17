package com.example.connecttointernet.model


import com.google.gson.annotations.SerializedName


data class Camera(
    @SerializedName("full_name")
    val fullName: String = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("name")
    val name: String = "",
    @SerializedName("rover_id")
    val roverId: Int = 0
)
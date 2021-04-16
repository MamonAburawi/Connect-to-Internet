package com.example.connecttointernet.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


/**
you can create account in open weather map and generate your api key..
use this link    ...   https://openweathermap.org/  ...

# this is the link if you want test your weather data..
https://api.openweathermap.org//data/2.5/weather?q=Enter_Your_City_Here&appid=Your_API_KEY

 **/


private const val BASE_URL = "https://api.openweathermap.org/"



 const val CITY = "paris" // enter city name here..
 const val API_KEY ="Your APi" // enter your api here ..





private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit = Retrofit.Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()



object ServiceApi {
    val retrofitService : WeatherApi by lazy { retrofit.create(WeatherApi::class.java)}
}
























//private val moshi = Moshi.Builder()
//    .add(KotlinJsonAdapterFactory())
//    .build()
//
//
//val retrofit = Retrofit.Builder()
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
//    .baseUrl(BASE_URL)
//    .build()
//
//
//
//
//object ServiceApi {
//    val retrofitService : UserApi by lazy { retrofit.create(UserApi::class.java) }
//}

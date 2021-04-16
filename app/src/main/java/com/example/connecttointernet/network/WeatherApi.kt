package com.example.connecttointernet.network



import com.example.connecttointernet.model.Weather
import retrofit2.http.GET



// https://api.openweathermap.org/data/2.5/weather?q=Enter_Your_City_Here&appid=Your_API_KEY



interface WeatherApi{
    @GET(value = "data/2.5/weather?q=$CITY&appid=$API_KEY")
    suspend fun getData(): Weather
}
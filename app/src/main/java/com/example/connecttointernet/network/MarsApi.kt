package com.example.connecttointernet.network



import com.example.connecttointernet.model.MarsData
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.*


// https://api.openweathermap.org/data/2.5/weather?q=Enter_Your_City_Here&appid=Your_API_KEY

//interface WeatherApi{
//    @GET(value = "data/2.5/weather?q=$CITY&appid=$API_KEY")
//    suspend fun getData(): Weather
//}
//



interface MarsApi{

    @GET("mars-photos/api/v1/rovers/curiosity/photos?sol=1000&api_key=$API_KEY")
    suspend fun getMars():Response<MarsData>


    // add commit

//    @GET("AhmedTawfik32/repo/users")
//    suspend fun getUsers(): Response<List<User>>
//
//    @GET("AhmedTawfik32/repo/users")
//    suspend fun getUserById(@Query("id") userId: Int): Response<User>
//
//    @POST("AhmedTawfik32/repo/users")
//    suspend fun addUser(@Body user: User): Response<User>
//
//    @PUT("AhmedTawfik32/repo/users")
//    suspend fun updateUser(@Body user: User, @Query("id") userId: Int):Response<User>
//
//    @DELETE("AhmedTawfik32/repo/users")
//    suspend fun deleteUserById(@Query("id") userId: Int): Response<User>
//


//    @POST("public/v2/users")
//    @Headers("Accept:application/json", "Content-Type:application/json","Authorization: Bearer $TOKEN")
//    suspend fun addUser(@Body user: UserData):Response<User>


//
//    @DELETE("https://gorest.co.in/public/v2/users/3912")
//    @Headers("Accept:application/json", "Content-Type:application/json",
//        "Authorization: Bearer $TOKEN")
//    suspend fun delete()
}



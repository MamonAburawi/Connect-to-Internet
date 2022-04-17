package com.example.connecttointernet.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.connecttointernet.model.Photo
import com.example.connecttointernet.network.ServiceApi
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.io.IOException


class UserViewModel() : ViewModel() {


    companion object{
        const val TAG = "UserViewModel"
    }


//    private val weatherData = runBlocking { ServiceApi.retrofitService.getData() }


    private val _data = MutableLiveData<List<Photo>?>()
    val data : LiveData<List<Photo>?> = _data

    private val _dataSize = MutableLiveData<Int>()
    val dataSize : LiveData<Int> = _dataSize


    private val viewModelScope = CoroutineScope(Dispatchers.Main + Job())



    init {
        refreshData()
    }



    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }


//     fun refresh(){
//        viewModelScope.launch {
//            val list = ServiceApi.retrofitService.getData()
//            _dataSize.value = list.size
//            _data.value = list[5]
//            Log.d("UserViewModel","users size: ${list.size}")
////            _data.value = list
//        }
//    }


    fun refreshData(){
        viewModelScope.launch {
           try {
                val response = ServiceApi.RETROFIT_SERVICE.getMars()
               if (response.isSuccessful && response.body() != null){
                   _data.value = response.body()!!.photos
                   _dataSize.value = response.body()!!.photos.size
                   Log.d(TAG,"items: ${_dataSize.value}")

                   val data = response.body()!!.photos

                   viewModelScope.launch {
                       data.forEach {
                           Log.d(TAG,"name: ${it.imgSrc}")
                       }
                   }



//                   data.forEach{
//                       Log.d(TAG,"uri: ${it.imgSrc}")
//                   }
               }
           }catch (ex: IOException){
               Log.d(TAG,"IOException: ${ex.message}")

           }catch (ex: HttpException){
               Log.d(TAG,"HttpException: ${ex.message}")
           }

        }
    }




    fun delete(){
        viewModelScope.launch {
            try {
//                ServiceApi.retrofitService.delete()
            }catch (ex: Exception){
                Log.d("UserViewModel",ex.message.toString())
            }
        }
    }


//    fun addNewUser(){
//        val user = UserData("ahemd@gmail.com","male", Random.nextInt(),"Ahmed Ali","online")
//
//        viewModelScope.launch {
//            val call = ServiceApi.retrofitService.addUser(user)
//            call.enqueue(object: Callback<UserResponse> {
//                override fun onFailure(call: Call<UserResponse>, t: Throwable) {
//                    Log.d(TAG,"failed to create user: ${t.message}")
//                }
//
//                override fun onResponse(call: Call<UserResponse>, response: Response<UserResponse>) {
//                    if(response.isSuccessful) {
//                        Log.d(TAG,"user created successfully")
//                    } else {
//                        Log.d(TAG,"failed to create user: error happing")
//                    }
//                }
//            })
//        }
//
//    }

}
package com.example.connecttointernet.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.connecttointernet.model.Weather
import com.example.connecttointernet.network.ServiceApi
import kotlinx.coroutines.*

class UserViewModel() : ViewModel() {

//private val _data = MutableLiveData<WeatherD>()
//    val data : LiveData<WeatherD> = _data
//


    private val _data = MutableLiveData<Weather>()
    val data : LiveData<Weather> = _data



    private val viewModelScope = CoroutineScope(Dispatchers.Main + Job())



    init {
        getData()
    }



    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }


    private fun getData(){
        viewModelScope.launch {
            _data.value = ServiceApi.retrofitService.getData()
        }
    }


}
package com.srikanth.locus.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.srikanth.locus.model.Weather
import com.srikanth.locus.di.RetroRepository
import com.srikanth.locus.retrofit.RetrofitService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherModel @Inject constructor(private val retrofitService: RetrofitService) : ViewModel() {

    private val liveData: MutableLiveData<Weather> = MutableLiveData()

    fun getLiveData(): MutableLiveData<Weather> {
        return liveData

    }

    fun getApi(query: String, key: String) {
        val retroRepo = RetroRepository(retrofitService)
        retroRepo.makeApiCall(query, key)
    }
}
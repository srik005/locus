package com.srikanth.locus.di

import android.util.Log.d
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.android.material.snackbar.Snackbar
import com.srikanth.locus.model.Weather
import com.srikanth.locus.model.WeatherList
import com.srikanth.locus.retrofit.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject


class RetroRepository @Inject constructor(private val retrofitService: RetrofitService) {
    val liveData = MutableLiveData<List<WeatherList>>()

    fun makeApiCall(query: String, appID: String) {
        val call = retrofitService.getData(query, appID)
        d("api call", call.request().url.toString())
        call.enqueue(object : Callback<Weather> {
            override fun onResponse(call: Call<Weather>, response: Response<Weather>) {
                if (response.isSuccessful) {
                    liveData.postValue(response.body()!!.weather)
                    d("api response", response.body().toString())

                } else {
                    liveData.postValue(null)
                }

            }

            override fun onFailure(call: Call<Weather>, t: Throwable) {
                liveData.postValue(null)
            }
        })
    }
}
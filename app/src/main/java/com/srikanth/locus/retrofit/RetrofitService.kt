package com.srikanth.locus.retrofit
import com.srikanth.locus.model.Weather
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("weather")
    fun getData(
        @Query("q") city: String,
        @Query("appId") appId: String,
    ): Call<Weather>
}
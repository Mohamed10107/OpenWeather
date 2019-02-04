package com.coderbot.openweather.network.api

import com.coderbot.openweather.network.response.WeatherResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherAPI {

    @GET("daily")
    @Headers("Content-Type: application/json", "Accept: application/json")
    fun getWeather(@Query("q") city: String, @Query("cnt") count: String, @Query("units") units: String, @Query("appid") appId: String): Single<WeatherResponse>

}
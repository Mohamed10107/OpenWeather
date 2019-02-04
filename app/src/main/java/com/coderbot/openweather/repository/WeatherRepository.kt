package com.coderbot.openweather.repository

import com.coderbot.openweather.model.Weather
import com.coderbot.openweather.network.api.WeatherAPI
import com.coderbot.openweather.network.response.WeatherResponse
import io.reactivex.Single
import io.reactivex.SingleEmitter
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class WeatherRepository @Inject constructor(protected val retrofit: Retrofit) {

    private var weather: ArrayList<Weather> = ArrayList()

    private var api: WeatherAPI = retrofit.create(WeatherAPI::class.java)

    fun getWeather(): Single<ArrayList<Weather>> {
        return Single.create {
            if (weather.isEmpty()) {
                getWeatherFromAPI(it)
            } else {
                it.onSuccess(weather)
            }
        }
    }

    private fun getWeatherFromAPI(it: SingleEmitter<ArrayList<Weather>>) {
        api.getWeather("cairo", "3", "metric", "92c8fc9f16eeee3a87ce33bffc3d939a").subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io()).map { mapResponse(it, "cairo") }.subscribe(
                object : SingleObserver<ArrayList<Weather>> {

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onSuccess(t: ArrayList<Weather>) {
                        weather = t
                        it.onSuccess(weather)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()
                        it.onError(e)
                    }
                }
            )
    }

    private fun mapResponse(response: WeatherResponse, city: String): ArrayList<Weather> {
        val weathers = ArrayList<Weather>()
        if (!response.list.isEmpty()) {
            for (i in response.list.indices) {
                val weather = Weather()
                weather.id = "$i"
                weather.city = city
                weather.date = response.list.get(i).dt
                weather.pressure = response.list.get(i).pressure
                weather.humidity = response.list.get(i).humidity
                weather.speed = response.list.get(i).speed
                weather.degree = response.list.get(i).deg
                weather.clouds = response.list.get(i).clouds
                weather.minTemperature = response.list.get(i).temp.min
                weather.maxTemperature = response.list.get(i).temp.max
                weather.dayTemperature = response.list.get(i).temp.day
                weather.nightTemperature = response.list.get(i).temp.night
                weather.eveningTemperature = response.list.get(i).temp.eve
                weather.morningTemperature = response.list.get(i).temp.morn
                weather.main = response.list.get(i).weather.get(0).main
                weather.description = response.list.get(i).weather.get(0).description
                weather.icon = "http://openweathermap.org/img/w/" + response.list.get(i).weather.get(0).icon + ".png"
                weathers.add(weather)
            }
        }
        return weathers
    }

}
package com.coderbot.openweather.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.coderbot.openweather.model.Weather
import com.coderbot.openweather.repository.WeatherRepository
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class WeatherViewModel @Inject constructor(protected var repository: WeatherRepository) : ViewModel() {

    private val weather: MutableLiveData<ArrayList<Weather>> = MutableLiveData()

    fun observeWeather(): MutableLiveData<ArrayList<Weather>> {
        return weather
    }

    fun getWeather() {
        repository.getWeather().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
            object : SingleObserver<ArrayList<Weather>> {

                override fun onSubscribe(d: Disposable) {

                }

                override fun onSuccess(t: ArrayList<Weather>) {
                    weather.postValue(t)
                }

                override fun onError(e: Throwable) {
                    weather.postValue(ArrayList())
                }
            }
        )
    }
}
package com.coderbot.openweather.view.weather

import com.coderbot.openweather.repository.WeatherRepository
import com.coderbot.openweather.viewmodel.WeatherViewModel
import dagger.Module
import dagger.Provides

@Module
class WeatherModule {

    @Provides
    fun provideWeatherViewModel(repository: WeatherRepository): WeatherViewModel {
        return WeatherViewModel(repository)
    }

}
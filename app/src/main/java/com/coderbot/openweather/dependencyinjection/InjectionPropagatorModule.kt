package com.coderbot.openweather.dependencyinjection

import com.coderbot.openweather.view.weather.WeatherActivity
import com.coderbot.openweather.view.weather.WeatherModule
import dagger.android.ContributesAndroidInjector
import dagger.Module

@Module
abstract class InjectionPropagatorModule {

    @ContributesAndroidInjector(modules = [WeatherModule::class])
    protected abstract fun injectWeatherActivity(): WeatherActivity

}
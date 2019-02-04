package com.coderbot.openweather.dependencyinjection

import com.coderbot.openweather.repository.WeatherRepository
import com.coderbot.openweather.utils.Constants.Companion.URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ProviderModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        return Retrofit.Builder().baseUrl(URL).client(client)
            .addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(
                RxJava2CallAdapterFactory.create()
            ).build()
    }

    @Singleton
    @Provides
    fun provideWeatherRepository(retrofit: Retrofit): WeatherRepository {
        return WeatherRepository(retrofit)
    }

}
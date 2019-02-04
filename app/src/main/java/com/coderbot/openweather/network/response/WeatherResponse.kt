package com.coderbot.openweather.network.response

import com.google.gson.annotations.SerializedName

class WeatherResponse {

    var list: ArrayList<WeatherModel> = ArrayList()

    class WeatherModel {

        @SerializedName("dt")
        lateinit var dt: String

        @SerializedName("pressure")
        lateinit var pressure: String

        @SerializedName("humidity")
        lateinit var humidity: String

        @SerializedName("speed")
        lateinit var speed: String

        @SerializedName("deg")
        lateinit var deg: String

        @SerializedName("clouds")
        lateinit var clouds: String

        @SerializedName("temp")
        var temp = Temperature()

        @SerializedName("weather")
        var weather: ArrayList<Weather> = ArrayList()
    }

    class Temperature {

        @SerializedName("day")
        lateinit var day: String

        @SerializedName("min")
        lateinit var min: String

        @SerializedName("max")
        lateinit var max: String

        @SerializedName("night")
        lateinit var night: String

        @SerializedName("eve")
        lateinit var eve: String

        @SerializedName("morn")
        lateinit var morn: String
    }

    class Weather {

        @SerializedName("id")
        lateinit var id: String

        @SerializedName("main")
        lateinit var main: String

        @SerializedName("description")
        lateinit var description: String

        @SerializedName("icon")
        lateinit var icon: String
    }

}
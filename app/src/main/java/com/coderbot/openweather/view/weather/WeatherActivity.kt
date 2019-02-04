package com.coderbot.openweather.view.weather

import android.arch.lifecycle.Observer
import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.AppCompatImageView
import android.view.View
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.coderbot.openweather.R
import com.coderbot.openweather.base.BaseActivity
import com.coderbot.openweather.model.Weather
import com.coderbot.openweather.view.about.AboutActivity
import com.coderbot.openweather.viewmodel.WeatherViewModel
import java.util.*
import javax.inject.Inject

class WeatherActivity : BaseActivity() {

    @BindView(R.id.weather)
    protected lateinit var weatherLayout: ConstraintLayout

    @BindView(R.id.icon)
    protected lateinit var icon: AppCompatImageView

    @BindView(R.id.iconT)
    protected lateinit var iconT: AppCompatImageView

    @BindView(R.id.iconAT)
    protected lateinit var iconAT: AppCompatImageView

    @BindView(R.id.today)
    protected lateinit var today: TextView

    @BindView(R.id.state)
    protected lateinit var state: TextView

    @BindView(R.id.max)
    protected lateinit var max: TextView

    @BindView(R.id.min)
    protected lateinit var min: TextView

    @BindView(R.id.humidity)
    protected lateinit var humidity: TextView

    @BindView(R.id.wind)
    protected lateinit var wind: TextView

    @BindView(R.id.tomorrow)
    protected lateinit var tomorrow: TextView

    @BindView(R.id.maxT)
    protected lateinit var maxT: TextView

    @BindView(R.id.minT)
    protected lateinit var minT: TextView

    @BindView(R.id.afterTomorrow)
    protected lateinit var afterTomorrow: TextView

    @BindView(R.id.maxAT)
    protected lateinit var maxAT: TextView

    @BindView(R.id.minAT)
    protected lateinit var minAT: TextView

    @Inject
    protected lateinit var viewModel: WeatherViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)
        ButterKnife.bind(this@WeatherActivity)

        initViews()
        initLiveDataListeners()

        viewModel.getWeather()
    }

    override fun initViews() {
        showLoading()
        weatherLayout.visibility = View.GONE
    }

    override fun initLiveDataListeners() {
        viewModel.observeWeather().observe(this@WeatherActivity, Observer {
            dismissLoading()
            if (it != null && !it.isEmpty()) {
                setData(it)
                weatherLayout.visibility = View.VISIBLE
            } else {
                Toast.makeText(this@WeatherActivity, R.string.error, Toast.LENGTH_LONG).show()
            }
        })
    }

    @OnClick(R.id.about)
    protected fun about() {
        startActivity(Intent(this@WeatherActivity, AboutActivity::class.java))
    }

    private fun setData(weather: ArrayList<Weather>) {
        setTodayData(weather[0])
        setTomorrowData(weather[1])
        setAfterTomorrowData(weather[2])
    }

    private fun setTodayData(weather: Weather) {
        val time = Calendar.getInstance()
        time.time = Date()
        val date = Date(1000 * java.lang.Long.parseLong(weather.date)).toString()
        today.text = getString(R.string.today, date.replace(date.substring(11, date.length - 4), ""))
        state.text = getString(R.string.state, weather.main, weather.dayTemperature)
        max.text = getString(R.string.max, weather.maxTemperature)
        min.text = getString(R.string.min, weather.minTemperature)
        humidity.text = getString(R.string.humidity, weather.humidity)
        wind.text = getString(R.string.wind, weather.speed)

        if (weather.main.toLowerCase().contains("clear")) {
            if (time.get(Calendar.HOUR_OF_DAY) in 5..18) {
                icon.setImageResource(R.drawable.sunny)
            } else {
                icon.setImageResource(R.drawable.night_clear)
            }
        } else if (weather.main.toLowerCase().contains("rain")) {
            icon.setImageResource(R.drawable.rainy)
        } else if (weather.main.toLowerCase().contains("cloud")) {
            if (time.get(Calendar.HOUR_OF_DAY) in 5..18) {
                icon.setImageResource(R.drawable.cloudy)
            } else {
                icon.setImageResource(R.drawable.night_cloudy)
            }
        } else if (weather.main.toLowerCase().contains("storm")) {
            icon.setImageResource(R.drawable.stormy)
        } else if (weather.main.toLowerCase().contains("snow")) {
            icon.setImageResource(R.drawable.snowy)
        }
    }

    private fun setTomorrowData(weather: Weather) {
        val time = Calendar.getInstance()
        time.time = Date()
        val date = Date(1000 * java.lang.Long.parseLong(weather.date)).toString()
        tomorrow.text = getString(R.string.today, date.replace(date.substring(11, date.length - 4), ""))
        maxT.text = getString(R.string.max, weather.maxTemperature)
        minT.text = getString(R.string.min, weather.minTemperature)

        if (weather.main.toLowerCase().contains("clear")) {
            if (time.get(Calendar.HOUR_OF_DAY) in 5..18) {
                iconT.setImageResource(R.drawable.sunny)
            } else {
                iconT.setImageResource(R.drawable.night_clear)
            }
        } else if (weather.main.toLowerCase().contains("rain")) {
            iconT.setImageResource(R.drawable.rainy)
        } else if (weather.main.toLowerCase().contains("cloud")) {
            if (time.get(Calendar.HOUR_OF_DAY) in 5..18) {
                iconT.setImageResource(R.drawable.cloudy)
            } else {
                iconT.setImageResource(R.drawable.night_cloudy)
            }
        } else if (weather.main.toLowerCase().contains("storm")) {
            iconT.setImageResource(R.drawable.stormy)
        } else if (weather.main.toLowerCase().contains("snow")) {
            iconT.setImageResource(R.drawable.snowy)
        }
    }

    private fun setAfterTomorrowData(weather: Weather) {
        val time = Calendar.getInstance()
        time.time = Date()
        val date = Date(1000 * java.lang.Long.parseLong(weather.date)).toString()
        afterTomorrow.text = getString(R.string.today, date.replace(date.substring(11, date.length - 4), ""))
        maxAT.text = getString(R.string.max, weather.maxTemperature)
        minAT.text = getString(R.string.min, weather.minTemperature)

        if (weather.main.toLowerCase().contains("clear")) {
            if (time.get(Calendar.HOUR_OF_DAY) in 5..18) {
                iconAT.setImageResource(R.drawable.sunny)
            } else {
                iconAT.setImageResource(R.drawable.night_clear)
            }
        } else if (weather.main.toLowerCase().contains("rain")) {
            iconAT.setImageResource(R.drawable.rainy)
        } else if (weather.main.toLowerCase().contains("cloud")) {
            if (time.get(Calendar.HOUR_OF_DAY) in 5..18) {
                iconAT.setImageResource(R.drawable.cloudy)
            } else {
                iconAT.setImageResource(R.drawable.night_cloudy)
            }
        } else if (weather.main.toLowerCase().contains("storm")) {
            iconAT.setImageResource(R.drawable.stormy)
        } else if (weather.main.toLowerCase().contains("snow")) {
            iconAT.setImageResource(R.drawable.snowy)
        }
    }
}
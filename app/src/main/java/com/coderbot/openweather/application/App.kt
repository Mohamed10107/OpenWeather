package com.coderbot.openweather.application

import android.app.Application
import android.app.Activity
import com.coderbot.openweather.dependencyinjection.DaggerInjectorComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class App : Application(), HasActivityInjector {

    @Inject
    lateinit var activityDispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()

        DaggerInjectorComponent.builder().application(this@App).build().inject(this@App)
    }

}
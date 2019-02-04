package com.coderbot.openweather.dependencyinjection

import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton
import android.app.Application
import com.coderbot.openweather.application.App
import dagger.BindsInstance

@Singleton
@Component(modules = [AndroidInjectionModule::class, ProviderModule::class, InjectionPropagatorModule::class])
interface InjectorComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): InjectorComponent
    }

}
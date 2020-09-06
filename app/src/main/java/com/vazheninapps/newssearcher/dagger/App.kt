package com.vazheninapps.newssearcher.dagger

import android.app.Application

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    companion object {
       private lateinit var component: AppComponent
        fun getComponent(): AppComponent {
            return component
        }
    }
}
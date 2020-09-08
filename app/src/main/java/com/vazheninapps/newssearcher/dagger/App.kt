package com.vazheninapps.newssearcher.dagger

import android.app.Application

class App : Application() {

    val appComponent:AppComponent by lazy { DaggerAppComponent.factory().create(applicationContext)}
}
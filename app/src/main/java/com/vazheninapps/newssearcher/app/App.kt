package com.vazheninapps.newssearcher.app

import android.app.Application
import com.vazheninapps.newssearcher.screens.news.dagger.DaggerNewsActivityComponent
import com.vazheninapps.newssearcher.screens.news.dagger.NewsActivityComponent
import com.vazheninapps.newssearcher.screens.news.dagger.NewsActivityModule

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        component = DaggerNewsActivityComponent.builder().newsActivityModule(NewsActivityModule(this)).build()
    }

    companion object {
       private lateinit var component: NewsActivityComponent
        fun getComponent(): NewsActivityComponent {
            return component
        }
    }
}
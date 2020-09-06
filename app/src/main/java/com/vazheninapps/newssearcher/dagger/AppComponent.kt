package com.vazheninapps.newssearcher.dagger
import com.vazheninapps.newssearcher.screens.news.dagger.NewsActivityComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkModule::class, SubComponentsModule::class])
interface AppComponent {

    fun newsActivityComponent(): NewsActivityComponent.Factory

}
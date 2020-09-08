package com.vazheninapps.newssearcher.dagger
import android.content.Context
import com.vazheninapps.newssearcher.screens.news.dagger.NewsActivityComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, SubComponentsModule::class])
interface AppComponent {

    @Component.Factory
    interface Factory{
       fun create(@BindsInstance context: Context):AppComponent
    }

    fun newsActivityComponent(): NewsActivityComponent.Factory

}
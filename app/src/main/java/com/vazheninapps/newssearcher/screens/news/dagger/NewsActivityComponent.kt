package com.vazheninapps.newssearcher.screens.news.dagger

import com.vazheninapps.newssearcher.dagger.ActivityScope
import com.vazheninapps.newssearcher.screens.news.mvp.NewsActivity
import dagger.Subcomponent
import java.io.Serializable

@ActivityScope
@Subcomponent(modules = [NewsActivityModule::class])
interface NewsActivityComponent: Serializable {

    @Subcomponent.Factory
    interface Factory {
        fun create(): NewsActivityComponent
    }

    fun inject(newsActivity: NewsActivity)
}
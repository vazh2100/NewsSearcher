package com.vazheninapps.newssearcher.screens.news.dagger

import com.vazheninapps.newssearcher.screens.news.mvp.NewsActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [NewsActivityModule::class])
interface NewsActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): NewsActivityComponent
    }

    fun inject(newsActivity: NewsActivity)
}
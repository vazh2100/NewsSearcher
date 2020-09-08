package com.vazheninapps.newssearcher.screens.news.dagger

import com.vazheninapps.newssearcher.dagger.ActivityScope
import com.vazheninapps.newssearcher.screens.news.mvp.NewsContract
import com.vazheninapps.newssearcher.screens.news.mvp.NewsModel
import com.vazheninapps.newssearcher.screens.news.mvp.NewsPresenter
import dagger.Binds
import dagger.Module


@Module
abstract class NewsActivityModule {

    @ActivityScope
    @Binds
    abstract fun provideNewsContractModel(newsModel:NewsModel):NewsContract.Model

    @ActivityScope
    @Binds
    abstract fun provideNewsContractPresenter(newsPresenter: NewsPresenter):NewsContract.Presenter

}
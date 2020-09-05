package com.vazheninapps.newssearcher.screens.news.dagger

import com.vazheninapps.newssearcher.adapters.ArticleAdapter
import com.vazheninapps.newssearcher.screens.news.mvp.NewsPresenter
import dagger.Component
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Singleton
@Component(modules = [NewsActivityModule::class])
interface NewsActivityComponent{

    fun getAdapter():ArticleAdapter
    fun getPresenter():NewsPresenter
    fun getCompositeDisposable():CompositeDisposable

}
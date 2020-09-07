package com.vazheninapps.newssearcher.screens.news.dagger

import android.content.Context
import com.vazheninapps.newssearcher.adapters.ArticleAdapter
import com.vazheninapps.newssearcher.api.NewsService
import com.vazheninapps.newssearcher.screens.news.mvp.NewsContract
import com.vazheninapps.newssearcher.screens.news.mvp.NewsModel
import com.vazheninapps.newssearcher.screens.news.mvp.NewsPresenter
import dagger.Module
import dagger.Provides


@Module
class NewsActivityModule {

    @ActivityScope
    @Provides
   fun provideNewsModel(context: Context, newsService:NewsService):NewsContract.Model{
      return NewsModel(context, newsService)
  }

    @ActivityScope
    @Provides
    fun provideNewsPresenter(model:NewsContract.Model):NewsContract.Presenter {
       return NewsPresenter.getInstance(model)
    }

    @ActivityScope
    @Provides
    fun provideArticleAdapter():ArticleAdapter{
        return ArticleAdapter.getInstance()
    }
}
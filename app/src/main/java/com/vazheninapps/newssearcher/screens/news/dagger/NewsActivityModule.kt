package com.vazheninapps.newssearcher.screens.news.dagger

import android.content.Context
import com.vazheninapps.newssearcher.adapters.ArticleAdapter
import com.vazheninapps.newssearcher.screens.news.mvp.NewsContract
import com.vazheninapps.newssearcher.screens.news.mvp.NewsModel
import com.vazheninapps.newssearcher.screens.news.mvp.NewsPresenter
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class NewsActivityModule(val context: Context) {

    @Provides
    fun provideContext():Context{
        return context.applicationContext
    }
    @Provides
   fun provideModel(context: Context):NewsContract.Model{
      return NewsModel(context)
  }



//    @Provides @Singleton
//    fun provideAdapter():ArticleAdapter{
//        return ArticleAdapter()
//    }
//
//    @Provides @Singleton
//    fun provideNewsPresenter(model:NewsContract.Model):NewsPresenter {
//        return NewsPresenter(model)
//    }


}
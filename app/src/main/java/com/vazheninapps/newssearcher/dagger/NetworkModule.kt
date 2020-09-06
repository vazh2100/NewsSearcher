package com.vazheninapps.newssearcher.dagger

import com.vazheninapps.newssearcher.api.NewsService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides  fun provideNewsService(): NewsService {
       return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(NewsService::class.java)
    }

    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"
    }

}
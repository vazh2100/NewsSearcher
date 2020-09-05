package com.vazheninapps.newssearcher.screens.news.mvp

import android.content.Context
import com.vazheninapps.newssearcher.R
import com.vazheninapps.newssearcher.api.ApiFactory
import com.vazheninapps.newssearcher.app.App
import com.vazheninapps.newssearcher.base.mvp.BaseModel
import com.vazheninapps.newssearcher.utils.NetUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class NewsModel(context: Context): BaseModel(context), NewsContract.Model {

    private val compositeDisposable by lazy { App.getComponent().getCompositeDisposable() }

    override fun loadArticles(q: String?, language: String, page: Int, loadCallback: NewsContract.Model.LoadCallback) {

       if( NetUtils.isInternetConnection(context)){
        val disposable = ApiFactory
            .newsService
            .getNews(q, language, page = page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it.articles != null && it.articles.isNotEmpty()) {
                    loadCallback.onLoad(it.articles)
                } else {
                    loadCallback.onError(context.getString(R.string.error_response_not_invalid_but_empty))
                } },
                {
                    loadCallback.onError(it.message)
                }
            )
        compositeDisposable.add(disposable)}
        else {
           loadCallback.onError(context.getString(R.string.error_no_internet_connection))
       }
    }

    override fun clearDisposable() {
    compositeDisposable.dispose()
    }


}
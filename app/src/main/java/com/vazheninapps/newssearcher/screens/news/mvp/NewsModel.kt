package com.vazheninapps.newssearcher.screens.news.mvp

import android.content.Context
import com.vazheninapps.newssearcher.R
import com.vazheninapps.newssearcher.api.NewsService
import com.vazheninapps.newssearcher.base.mvp.BaseModel
import com.vazheninapps.newssearcher.utils.NetUtils
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewsModel @Inject constructor(context: Context, private val newsService: NewsService): BaseModel(context), NewsContract.Model {

    private val compositeDisposable by lazy { CompositeDisposable()}


    override fun loadArticles(q: String?, language: String, page: Int, loadCallback: NewsContract.Model.LoadCallback) {

       if( NetUtils.isInternetConnection(context)){
        val disposable = newsService
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
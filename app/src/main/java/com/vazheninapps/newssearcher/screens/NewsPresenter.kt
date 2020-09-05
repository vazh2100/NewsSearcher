package com.vazheninapps.newssearcher.screens

import android.net.Uri
import com.vazheninapps.newssearcher.base.mvp.BasePresenter
import com.vazheninapps.newssearcher.pojo.Article
import java.util.*

class NewsPresenter(model: NewsContract.Model ): BasePresenter<NewsContract.View, NewsContract.Model>(model), NewsContract.Presenter {

    private var isFirstLaunch = true
    private var isLoading = false
    private var query:String? = "sports"

    override fun viewIsReady() {
        isFirstLaunch = false
        if(!isLoading){
        loadArticles(query, 1)}
    }

    override fun buttonSearchClicked() {
        query = getView()?.getQueryString()
        getView()?.clearArticles()
        loadArticles(query, 1)
    }

    override fun endReached(page: Int) {
        if (!isLoading){
        loadArticles(query, page)}
    }

    override fun buttonToBrowserClicked(article: Article) {
        getView()?.goToBrowser(Uri.parse(article.url))
    }

   private fun loadArticles(q:String?, page:Int) {
        val lang = Locale.getDefault().language.toString()
        isLoading = true
        getView()?.showProgressBar()
        getModel()?.loadArticles(q = q, language = lang, page = page,
            loadCallback = object : NewsContract.Model.LoadCallback {
                override fun onLoad(articles: List<Article>) {
                    getView()?.showArticles(articles)
                    isLoading =false
                    getView()?.hideProgressBar()
                }

                override fun onError(message: String?) {
                   getView()?.showError(message)
                    isLoading = false
                    getView()?.hideProgressBar()
                }

            })

    }

    override fun readyToShowAnimation() {
        if(isFirstLaunch){
            getView()?.showSplashAnimation()
        }
    }

    override fun destroy() {
        getModel()?.clearDisposable()
        super.destroy()

    }

    companion object{
        private  var  presenter:NewsPresenter? = null
        fun getInstance(model:NewsContract.Model):NewsPresenter{
            presenter?.let{return it }
            val instance = NewsPresenter(model)
            presenter =instance
            return instance
        }
    }
}
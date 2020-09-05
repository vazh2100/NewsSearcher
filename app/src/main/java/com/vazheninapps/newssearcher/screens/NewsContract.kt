package com.vazheninapps.newssearcher.screens

import android.net.Uri
import com.vazheninapps.newssearcher.base.mvp.MVPModel
import com.vazheninapps.newssearcher.base.mvp.MVPPresenter
import com.vazheninapps.newssearcher.base.mvp.MVPView
import com.vazheninapps.newssearcher.pojo.Article

interface NewsContract {

    interface View :MVPView{
        fun getQueryString():String
        fun clearArticles()
        fun showArticles(articles: List<Article>)
        fun showError (errorMessage: String?)
        fun goToBrowser(uri: Uri)
        fun showProgressBar()
        fun hideProgressBar()
        fun showSplashAnimation()
    }

    interface Model:MVPModel{
        interface LoadCallback{
            fun onLoad(articles: List<Article>)
            fun onError(message: String?)
        }

        fun loadArticles(q:String? = "android", language:String = "ru", page:Int = 1, loadCallback: LoadCallback)
        fun clearDisposable()
    }

    interface Presenter :MVPPresenter<View, Model>{
        fun buttonSearchClicked()
        fun endReached(page:Int)
        fun buttonToBrowserClicked(article:Article)
        fun readyToShowAnimation()

    }


}
package com.vazheninapps.newssearcher.base.mvp


interface MVPPresenter<V : MVPView, M:MVPModel> {


    fun attachView(mvpView: V)



    fun viewIsReady()

    fun detachView()

    fun destroy()
}
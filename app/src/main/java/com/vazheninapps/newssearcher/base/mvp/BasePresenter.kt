package com.vazheninapps.newssearcher.base.mvp

abstract class BasePresenter<V : MVPView, M:MVPModel>(private var model: M?) : MVPPresenter<V, M> {

    private  var view: V? = null


    override fun attachView(mvpView: V) {
        view = mvpView
    }

    override fun detachView() {
        view = null
    }

    protected val isViewAttached: Boolean
        get() = view != null

    override fun destroy() {
        model = null
    }


    fun getView(): V? {
        return view
    }
    fun getModel():M? {
        return  model
    }
}
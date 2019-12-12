package io.mediaportal.ironsource.base


abstract class BasePresenter<out V>{
    private var view: V? = null

    abstract fun initialise()
    abstract fun disposeSubscriptions()

    fun getView(): V? = view

    fun attachView(view: Any?) {
        this.view = view as V?
    }

    fun detachView() {
        view = null
    }

}
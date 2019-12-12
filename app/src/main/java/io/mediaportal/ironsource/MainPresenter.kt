package io.mediaportal.ironsource

import io.mediaportal.ironsource.base.BasePresenter
import io.mediaportal.ironsource.model.ButtonToAction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Named


class MainPresenter @Inject constructor(
    @Named("network")
    val networkScope: CoroutineScope,
    @Named("ui")
    val uiScope: CoroutineScope,
    val endpoint: ButtonActionEndpoint
) : BasePresenter<MainView>() {

    private val job: Job? = null

    override fun initialise() {
        getView()?.setState(State.LOADING)
        loadButtonAction()
    }

    override fun disposeSubscriptions() {
    }

    private fun loadButtonAction() {
        networkScope.launch {
            val response = endpoint.getButtonAsAction()
            uiScope.launch {
                if (response.isSuccessful && response.body() != null) {
                    getView()?.setState(State.SUCCESS)
                    getView()?.setActionToButton(getActiveAction(response.body()!!))
                } else getView()?.setState(State.ERROR)
            }
        }
    }

    private fun getActiveAction(actions: List<ButtonToAction>): ButtonToAction {
        return actions.filter { it.enabled }
//            .filter { it.cool_down } //TODO: SharedPreference module
//            .filter { it.valid_days } //TODO: SharedPreference module
            .sortedBy { it.priority }
            .first()
    }

}
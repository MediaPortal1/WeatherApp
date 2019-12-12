package io.mediaportal.ironsource

import io.mediaportal.ironsource.model.ButtonToAction


interface MainView{
    fun setState(state: State)
    fun setActionToButton(type: ButtonToAction)
}
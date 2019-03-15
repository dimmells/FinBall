package com.its.mobile.finball.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.interact.MainInteract
import com.its.mobile.finball.presentation.view.MainView

@InjectViewState
class MainPresenter(private val mainInteract: MainInteract) : BaseMvpPresenter<MainView>()


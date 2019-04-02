package com.its.mobile.finball.presentation.view

import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface IntroSlideView: BaseMvpView {

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setLogoImage(imageId: Int)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setTitle(strId: Int?)

    @StateStrategyType(AddToEndSingleStrategy::class)
    fun setContent(strId: Int?)
}
package com.its.mobile.finball.di.component.intro

import com.its.mobile.finball.di.module.intro.PrivatePolicyModule
import com.its.mobile.finball.di.scope.ViewScope
import com.its.mobile.finball.presentation.presenter.intro.PrivatePolicyPresenter
import dagger.Subcomponent

@Subcomponent(modules = [PrivatePolicyModule::class])
@ViewScope
interface PrivatePolicyComponent {
    fun privatePolicyPresenter(): PrivatePolicyPresenter
}
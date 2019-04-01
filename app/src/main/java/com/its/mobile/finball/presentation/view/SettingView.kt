package com.its.mobile.finball.presentation.view

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType

interface SettingView: BaseMvpView {

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun showToast(text: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun saveBackupData()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun shareBackupData(path: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun checkPermissions(requestCode: Int)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun chooseImportFile()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun notifySettingItemsChanged()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun notifySettingItemChanged(position: Int)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun startRateIntent()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun shareApp()

}
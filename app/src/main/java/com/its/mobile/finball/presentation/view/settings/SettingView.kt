package com.its.mobile.finball.presentation.view.settings

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType
import com.its.mobile.finball.presentation.view.BaseMvpView

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

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToAboutApp()

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun navigateToWriteUs()

}
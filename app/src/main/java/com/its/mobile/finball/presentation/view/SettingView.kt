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
    fun setImport(text: String)

    @StateStrategyType(OneExecutionStateStrategy::class)
    fun chooseImportFile()
}
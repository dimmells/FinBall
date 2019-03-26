package com.its.mobile.finball.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.interact.SettingInteract
import com.its.mobile.finball.presentation.view.SettingView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File

@InjectViewState
class SettingPresenter(private val settingInteract: SettingInteract): BaseMvpPresenter<SettingView>() {

    fun onExportClick() {
        viewState.checkPermissions()
    }

    fun onPermissionsGranted() {
        prepareExportData()
    }

    private fun prepareExportData() {
        loadCosts()
    }

    private fun loadCosts() {
        settingInteract.getCosts()
            .subscribe(
                { loadRevenue() },
                { viewState.showToast(it.localizedMessage) }
            )
            .let { disposables.add(it) }
    }

    private fun loadRevenue() {
        settingInteract.getRevenue()
            .subscribe(
                { loadSubCategory() },
                { viewState.showToast(it.localizedMessage) }
            )
            .let { disposables.add(it) }
    }

    private fun loadSubCategory() {
        settingInteract.getSubCategories()
            .subscribe(
                { viewState.saveBackupData() },
                { viewState.showToast(it.localizedMessage) }
            )
            .let { disposables.add(it) }
    }

    fun saveToFile(path: File) {
        settingInteract.writeToFile(path)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { filePath ->
                    viewState.showToast("Saved")
                    viewState.shareBackupData(filePath)
                },
                { viewState.showToast(it.localizedMessage) }
            )
            .let { disposables.add(it) }
    }
}
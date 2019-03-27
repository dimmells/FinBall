package com.its.mobile.finball.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.data.database.costs.CostsEntity
import com.its.mobile.finball.data.database.revenue.RevenueEntity
import com.its.mobile.finball.data.database.subCategory.SubCategoryEntity
import com.its.mobile.finball.interact.SettingInteract
import com.its.mobile.finball.presentation.view.SettingView
import com.its.mobile.finball.ui.fragment.SettingFragment
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.util.*

@InjectViewState
class SettingPresenter(private val settingInteract: SettingInteract): BaseMvpPresenter<SettingView>() {

    private lateinit var jsonCostsImportList: JSONArray
    private lateinit var jsonRevenueImportList: JSONArray
    private lateinit var jsonSubCategoryImportList: JSONArray

    fun onExportClick() { viewState.checkPermissions(SettingFragment.STORAGE_EXPORT_PERMISSIONS_CODE) }

    fun onImportClick() { viewState.checkPermissions(SettingFragment.STORAGE_IMPORT_PERMISSIONS_CODE) }

    fun prepareExportData() { loadCosts() }

    fun startImportData() { viewState.chooseImportFile() }

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

    fun readFromImportFile(importFile: File) {
        settingInteract.readImportFile(importFile)
            .subscribe(
                { jsonString ->
                    parseFromJson(jsonString)
                },
                { viewState.showToast(it.localizedMessage) }
            )
            .let { disposables.add(it) }
    }

    private fun parseFromJson(jsonString: String) {
        val jsonObject = JSONObject(jsonString)

        Completable.fromAction {
            jsonCostsImportList = jsonObject.getJSONArray(SettingInteract.COSTS_ARRAY_TITLE)
            jsonRevenueImportList = jsonObject.getJSONArray(SettingInteract.REVENUE_ARRAY_TITLE)
            jsonSubCategoryImportList = jsonObject.getJSONArray(SettingInteract.SUBCATEGORY_ARRAY_TITLE)

            insertSubCategoryArray()
            insertCostsArray()
            insertRevenueArray()
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { viewState.showToast("Import successful") },
                { viewState.showToast(it.localizedMessage) }
            )
            .let { disposables.add(it) }
    }

    private fun insertSubCategoryArray() {
        for (i in 0 until jsonSubCategoryImportList.length()) {
            val item = jsonSubCategoryImportList.getJSONObject(i)
            settingInteract.insertSubCategory(
                SubCategoryEntity(
                    item.getInt("id"),
                    item.getInt("parentCategory"),
                    item.getString("title")
                )
            )
                .subscribe(
                    {},
                    { viewState.showToast(it.localizedMessage) }
                )
                .let { disposables.add(it) }
        }
    }

    private fun insertCostsArray() {
        for (i in 0 until jsonCostsImportList.length()) {
            val item = jsonCostsImportList.getJSONObject(i)
            settingInteract.insertCosts(
                CostsEntity(
                    Date(Date.parse(item.getString("date"))),
                    item.getString("categoryId").toInt(),
                    item.getString("amount").toFloat()
                )
            )
                .subscribe(
                    {},
                    { viewState.showToast(it.localizedMessage) }
                )
                .let { disposables.add(it) }
        }
    }

    private fun insertRevenueArray() {
        for (i in 0 until jsonRevenueImportList.length()) {
            val item = jsonRevenueImportList.getJSONObject(i)
            settingInteract.insertRevenue(
                RevenueEntity(
                    Date(Date.parse(item.getString("date"))),
                    item.getInt("categoryId"),
                    item.getString("amount").toFloat()
                )
            )
                .subscribe(
                    {},
                    { viewState.showToast(it.localizedMessage) }
                )
                .let { disposables.add(it) }
        }
    }
}
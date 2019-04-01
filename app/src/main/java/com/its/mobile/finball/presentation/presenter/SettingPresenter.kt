package com.its.mobile.finball.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.its.mobile.finball.data.database.costs.CostsEntity
import com.its.mobile.finball.data.database.revenue.RevenueEntity
import com.its.mobile.finball.data.database.subCategory.SubCategoryEntity
import com.its.mobile.finball.data.setting.SettingEntity
import com.its.mobile.finball.data.setting.SettingProperty
import com.its.mobile.finball.interact.SettingInteract
import com.its.mobile.finball.presentation.adapter.SettingsAdapterContracts
import com.its.mobile.finball.presentation.view.SettingView
import com.its.mobile.finball.ui.fragment.SettingFragment
import com.its.mobile.finball.ui.item.SettingItem
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.util.*

@InjectViewState
class SettingPresenter(private val settingInteract: SettingInteract): BaseMvpPresenter<SettingView>(),
    SettingsAdapterContracts.AdapterPresenter, SettingsAdapterContracts.Presenter{

    private val settingList: ArrayList<SettingEntity> = ArrayList()
    private var settingPropertiesMap: MutableMap<String, Any> = hashMapOf()

    private lateinit var jsonCostsImportList: JSONArray
    private lateinit var jsonRevenueImportList: JSONArray
    private lateinit var jsonSubCategoryImportList: JSONArray

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadSettingsItems()
    }

    override fun getSettingsItemsCount(): Int = settingList.size

    override fun onBindSettingItemView(view: SettingsAdapterContracts.View, position: Int) {
        val setting = settingList[position]
        val property = settingPropertiesMap[setting.itemId]

        with(view) {
            setTitle(setting.title)
            setIcon(setting.iconTrue)
            if (property is Boolean) {
                if (!property) {
                    setting.iconFalse?.let { setIcon(it) }
                }
            }
            setTopDividerVisible(position != 0)
            setBottomDividerVisible(position == settingList.size - 1)
        }
    }

    override fun onItemClick(position: Int) {
        if (position in 0 until settingList.size) {
            when (settingList[position].itemId) {
                SettingItem.NOTIFICATIONS -> switchProperty(position)
                SettingItem.NOTIFICATIONS_SOUND -> switchProperty(position)
                SettingItem.IMPORT_DATA -> onImportClick()
                SettingItem.EXPORT_DATA -> onExportClick()
                SettingItem.RATE_APP -> viewState.startRateIntent()
                SettingItem.SHARE_APP -> viewState.shareApp()
                SettingItem.WRITE_US -> viewState.showToast("Write us")
                SettingItem.ABOUT_APP -> viewState.navigateToAboutApp()
            }
        }
    }

    private fun switchProperty(position: Int) {
        val itemId = settingList[position].itemId
        val value = settingPropertiesMap[itemId]

        if (value is Boolean) {
            settingInteract.setProperty(SettingProperty(itemId, !value))
            viewState.notifySettingItemChanged(position)
        }
    }

    private fun loadSettingsItems() {
        settingList.addAll(settingInteract.getSettingsEntityList())
        settingInteract.getPropertyMap()
            .subscribe { settingPropertiesMap = it }
            .let { disposables.add(it) }
        viewState.notifySettingItemsChanged()
    }

    private fun onExportClick() { viewState.checkPermissions(SettingFragment.STORAGE_EXPORT_PERMISSIONS_CODE) }

    private fun onImportClick() { viewState.checkPermissions(SettingFragment.STORAGE_IMPORT_PERMISSIONS_CODE) }

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
package com.its.mobile.finball.interact

import com.google.gson.GsonBuilder
import com.its.mobile.finball.data.database.costs.CostsDBManager
import com.its.mobile.finball.data.database.costs.CostsEntity
import com.its.mobile.finball.data.database.revenue.RevenueDBManager
import com.its.mobile.finball.data.database.revenue.RevenueEntity
import com.its.mobile.finball.data.database.subCategory.SubCategoryDBManager
import com.its.mobile.finball.data.database.subCategory.SubCategoryEntity
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.json.JSONArray
import org.json.JSONObject
import java.io.*
import java.text.SimpleDateFormat
import java.util.*

class SettingInteract(
    private val costsDBManager: CostsDBManager,
    private val revenueDBManager: RevenueDBManager,
    private val subCategoryDBManager: SubCategoryDBManager
) {

    companion object {
        const val COSTS_ARRAY_TITLE = "costs"
        const val REVENUE_ARRAY_TITLE = "revenue"
        const val SUBCATEGORY_ARRAY_TITLE = "subCategory"
    }

    private var jsonCostsExportList: String = ""
    private var jsonRevenueExportList: String = ""
    private var jsonSubCategoryExportList: String = ""

    fun getCosts(): Single<List<CostsEntity>> = costsDBManager.getAll()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess { costsList ->
            val gson = GsonBuilder().setPrettyPrinting().create()
            jsonCostsExportList = "\"" + COSTS_ARRAY_TITLE + "\": ".plus(gson.toJson(costsList))
            getRevenue()
        }

    fun getRevenue(): Single<List<RevenueEntity>> = revenueDBManager.getAll()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess { revenueList ->
            val gson = GsonBuilder().setPrettyPrinting().create()
            jsonRevenueExportList = "\"" + REVENUE_ARRAY_TITLE + "\": ".plus(gson.toJson(revenueList))
            getSubCategories()
        }

    fun getSubCategories(): Single<List<SubCategoryEntity>> = subCategoryDBManager.getAll()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess { subCategoryList ->
            val gson = GsonBuilder().setPrettyPrinting().create()
            jsonSubCategoryExportList = "\"" + SUBCATEGORY_ARRAY_TITLE + "\": ".plus(gson.toJson(subCategoryList))
        }

    fun writeToFile(pathDir: File): Single<String> =
        Single.fromCallable {
            if (!pathDir.exists()) {
                pathDir.mkdirs()
            }
            val pathFile = pathDir.path + File.separator + "FinBallFrom " + SimpleDateFormat(
                "dd.MM.yyyy-HH:mm:ss",
                Locale.ROOT
            ).format(Calendar.getInstance().time) + ".json"
            val file = File(pathFile)
            file.createNewFile()
            val fileWriter = FileWriter(file)
            fileWriter.append("{")
            fileWriter.append(jsonCostsExportList)
            fileWriter.append(",\n")
            fileWriter.append(jsonRevenueExportList)
            fileWriter.append(",\n")
            fileWriter.append(jsonSubCategoryExportList)
            fileWriter.append("}")
            fileWriter.close()
            pathFile
        }

    fun readImportFile(importFile: File): Single<String> =
        Single.fromCallable {
            val fis = FileInputStream(importFile)
            val bfr = BufferedReader(InputStreamReader(fis))
            var result = ""
            var line = bfr.readLine()
            while (line != null) {
                result = result.plus(line)
                line = bfr.readLine()
            }
            result
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())

    fun insertCosts(costsEntity: CostsEntity): Single<Long> = costsDBManager.insert(costsEntity)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun insertRevenue(revenueEntity: RevenueEntity): Single<Long> = revenueDBManager.insert(revenueEntity)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    fun insertSubCategory(subCategoryEntity: SubCategoryEntity) = subCategoryDBManager.insert(subCategoryEntity)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

}
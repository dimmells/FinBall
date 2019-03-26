package com.its.mobile.finball.interact

import com.google.gson.GsonBuilder
import com.its.mobile.finball.data.database.costs.CostsDBManager
import com.its.mobile.finball.data.database.costs.CostsEntity
import com.its.mobile.finball.data.database.revenue.RevenueDBManager
import com.its.mobile.finball.data.database.revenue.RevenueEntity
import com.its.mobile.finball.data.database.subCategory.SubCategoryDBManager
import com.its.mobile.finball.data.database.subCategory.SubCategoryEntity
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.text.SimpleDateFormat
import java.util.*

class SettingInteract(private val costsDBManager: CostsDBManager, private val revenueDBManager: RevenueDBManager, private val subCategoryDBManager: SubCategoryDBManager) {

    private var jsonCostsExportList: String = ""
    private var jsonCRevenueExportList: String = ""
    private var jsonSubCategoryExportList: String = ""

    fun getCosts(): Single<List<CostsEntity>> = costsDBManager.getAll()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess { costsList ->
            val gson = GsonBuilder().setPrettyPrinting().create()
            jsonCostsExportList = "\"costs\": ".plus(gson.toJson(costsList))
            getRevenue()
        }

    fun getRevenue(): Single<List<RevenueEntity>> = revenueDBManager.getAll()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess { revenueList ->
            val gson = GsonBuilder().setPrettyPrinting().create()
            jsonCRevenueExportList = "\"revenue\": ".plus(gson.toJson(revenueList))
            getSubCategories()
        }

    fun getSubCategories(): Single<List<SubCategoryEntity>> = subCategoryDBManager.getAll()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .doOnSuccess { subCategoryList ->
            val gson = GsonBuilder().setPrettyPrinting().create()
            jsonSubCategoryExportList = "\"subCategory\": ".plus(gson.toJson(subCategoryList))
        }

    fun writeToFile(pathDir: File): Single<String> =
        Single.fromCallable {
            if (!pathDir.exists()) {
                pathDir.mkdirs()
            }
            val pathFile = pathDir.path + File.separator + "FinBallFrom " + SimpleDateFormat("dd.MM.yyyy-HH:mm:ss", Locale.ROOT).format(Calendar.getInstance().time) + ".json"
            val file = File(pathFile)
            file.createNewFile()
            val fileWriter = FileWriter(file)
            fileWriter.append("{")
            fileWriter.append(jsonCostsExportList)
            fileWriter.append(",\n")
            fileWriter.append(jsonCRevenueExportList)
            fileWriter.append(",\n")
            fileWriter.append(jsonSubCategoryExportList)
            fileWriter.append("}")
            fileWriter.close()
            pathFile
        }

}
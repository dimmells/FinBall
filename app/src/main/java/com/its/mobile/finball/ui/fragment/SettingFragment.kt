package com.its.mobile.finball.ui.fragment

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ShareCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.its.mobile.finball.BuildConfig
import com.its.mobile.finball.R
import com.its.mobile.finball.di.ApplicationLoader
import com.its.mobile.finball.di.module.SettingModule
import com.its.mobile.finball.presentation.presenter.SettingPresenter
import com.its.mobile.finball.presentation.view.SettingView
import com.its.mobile.finball.ui.adapter.SettingsRecyclerAdapter
import com.its.mobile.finball.ui.navigation.MainRouter
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import java.io.File


class SettingFragment : BaseFragment(), SettingView {

    companion object {
        const val STORAGE_EXPORT_PERMISSIONS_CODE = 1
        const val STORAGE_IMPORT_PERMISSIONS_CODE = 2
        const val PICKFILE_RERQUEST_CODE = 3

        const val SHARED_PROVIDER_AUTHORITY = BuildConfig.APPLICATION_ID + ".myfileprovider"

        fun newInstance(): SettingFragment = SettingFragment()
    }

    private val permissionsList =
        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private var isPermissionsGranted = true
    private lateinit var recyclerAdapter: SettingsRecyclerAdapter

    @InjectPresenter
    lateinit var settingPresenter: SettingPresenter

    @ProvidePresenter
    fun providePresenter(): SettingPresenter = ApplicationLoader.applicationComponent
        .settingComponent(SettingModule())
        .providePresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_setting, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layout_setting_toolbar.text_view_toolbar_title.text = "Setting"
        layout_setting_toolbar.button_toolbar_back.setOnClickListener { fragmentManager?.popBackStack() }

        recyclerAdapter = SettingsRecyclerAdapter(settingPresenter, settingPresenter)
        val recyclerLayoutManager = LinearLayoutManager(context)
        recycler_view_setting_list.adapter = recyclerAdapter
        recycler_view_setting_list.layoutManager = recyclerLayoutManager
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICKFILE_RERQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val fileUri = data?.data
            val file = File(fileUri?.path)
            settingPresenter.readFromImportFile(file)
        }
    }

    override fun saveBackupData() {
        settingPresenter.saveToFile(
            File(
                Environment.getExternalStorageDirectory(),
                context?.getString(R.string.app_name) + File.separator + "backup"
            )
        )
    }

    override fun shareBackupData(path: String) {
        val context = context ?: return

        val sharedFile = File(path)
        val uri = FileProvider.getUriForFile(context, SHARED_PROVIDER_AUTHORITY, sharedFile)

        val intentBuilder = ShareCompat.IntentBuilder.from(activity)
            .setType("image/*")
            .addStream(uri)

        val chooserIntent = intentBuilder.createChooserIntent()
        startActivity(chooserIntent)
    }

    override fun checkPermissions(requestCode: Int) {
        isPermissionsGranted = true
        context?.let { context ->
            permissionsList.forEach {
                if (ContextCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED)
                    isPermissionsGranted = false
            }
        }
        if (isPermissionsGranted) {
            when (requestCode) {
                STORAGE_EXPORT_PERMISSIONS_CODE -> settingPresenter.prepareExportData()
                STORAGE_IMPORT_PERMISSIONS_CODE -> settingPresenter.startImportData()
            }
        }
        else {
            requestPermissions(permissionsList, requestCode)
        }
    }

    override fun chooseImportFile() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "*/*"
        startActivityForResult(intent, PICKFILE_RERQUEST_CODE)
    }

    override fun notifySettingItemsChanged() = recyclerAdapter.notifyDataSetChanged()

    override fun notifySettingItemChanged(position: Int) = recyclerAdapter.notifyItemChanged(position)

    override fun startRateIntent() {
        val appPackageName = context?.packageName
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$appPackageName")))
    }

    override fun shareApp() {
        try {
            val appPackageName = context?.packageName
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "text/plain"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Share")
            val appLink = "https://play.google.com/store/apps/details?id=$appPackageName"
            val messageContent = "Try this app"
            shareIntent.putExtra(Intent.EXTRA_TEXT, "\n$messageContent\n$appLink\n")
            startActivity(Intent.createChooser(shareIntent, "Choose"))
        } catch (e: Exception) {
        }
    }

    override fun navigateToAboutApp() = (router as MainRouter).navigateToAboutApp()
}
package com.its.mobile.finball.ui.fragment

import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.support.v4.app.ShareCompat
import android.support.v4.content.ContextCompat
import android.support.v4.content.FileProvider
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
import kotlinx.android.synthetic.main.fragment_setting.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import java.io.File


class SettingFragment : BaseFragment(), SettingView {

    companion object {
        const val STORAGE_PERMISSIONS_CODE = 1
        const val PICKFILE_RERQUEST_CODE = 2

        fun newInstance(): SettingFragment = SettingFragment()
    }


    private val SHARED_PROVIDER_AUTHORITY = BuildConfig.APPLICATION_ID + ".myfileprovider"
    private val SHARED_FOLDER = "FinBall/backup"

    private val permissionsList =
        arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
    private var isPermissionsGranted = true

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

        button_setting_export.setOnClickListener { settingPresenter.onExportClick() }
        button_setting_import.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "*/*"
            startActivityForResult(intent, PICKFILE_RERQUEST_CODE)
        }
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

    override fun checkPermissions() {
        isPermissionsGranted = true
        context?.let { context ->
            permissionsList.forEach {
                if (ContextCompat.checkSelfPermission(context, it) != PackageManager.PERMISSION_GRANTED)
                    isPermissionsGranted = false
            }
        }
        if (isPermissionsGranted)
            settingPresenter.onPermissionsGranted()
        else {
            requestPermissions(permissionsList, STORAGE_PERMISSIONS_CODE)
        }
    }

    override fun setImport(text: String) {
        text_view_setting_import_text.text = text
    }
}
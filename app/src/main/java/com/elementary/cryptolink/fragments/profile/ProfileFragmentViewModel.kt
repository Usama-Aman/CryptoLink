package com.elementary.cryptolink.fragments.profile

import android.Manifest
import android.content.ComponentName
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Parcelable
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.lifecycle.ViewModel
import com.elementary.cryptolink.utils.AppUtils
import com.elementary.cryptolink.utils.AppUtils.isEmailValid
import com.elementary.cryptolink.utils.Constants
import java.io.File


class ProfileFragmentViewModel : ViewModel() {

    private lateinit var profileFragment: ProfileFragment
    private var profileName = ""
    private var profileEmail = ""
    private var profileSites = ""
    private var profilePointsGained = ""

    lateinit var outputFileUri: Uri

    fun init(profileFragment: ProfileFragment) {
        this.profileFragment = profileFragment
    }

    val profileNameWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            profileName = s.toString()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }

    val profileEmailWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            profileEmail = s.toString()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }

    val profileSiteWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            profileSites = s.toString()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }

    val profilePointsWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            profilePointsGained = s.toString()
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

        }
    }

    fun profileSave(view: View) {
        AppUtils.preventDoubleClick(view)

        if (profileName.isBlank()) {
            AppUtils.showToast(profileFragment.activity!!, "Name is required", false)
            return
        }
        if (profileEmail.isBlank()) {
            AppUtils.showToast(profileFragment.activity!!, "Email is required", false)
            return
        }
        if (!profileEmail.isEmailValid()) {
            AppUtils.showToast(profileFragment.activity!!, "Email is invalid", false)
            return
        }

    }

    fun selectProfile(view: View) {
        AppUtils.preventDoubleClick(view)
        profileFragment.checkPermissions()
    }

    fun backClicked(v: View) {
        AppUtils.preventDoubleClick(v)
        profileFragment.activity?.supportFragmentManager?.popBackStack()
    }

    fun openImageIntent() {

        // Determine Uri of camera image to save.
        val root = File(profileFragment.activity?.getExternalFilesDir(""), File.separator.toString() + "CryptoLink" + File.separator)

        root.mkdir()
        val fname = "img_" + System.currentTimeMillis() + ".jpg"
        val sdImageMainDirectory = File(root, fname)
        outputFileUri = Uri.fromFile(sdImageMainDirectory)

        // Camera.
        val cameraIntents: ArrayList<Intent> = ArrayList()
        val captureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val packageManager: PackageManager = profileFragment.activity?.packageManager!!
        val listCam = packageManager.queryIntentActivities(captureIntent, 0)
        for (res in listCam) {
            val packageName = res.activityInfo.packageName
            val intent = Intent(captureIntent)
            intent.component = ComponentName(res.activityInfo.packageName, res.activityInfo.name)
            intent.setPackage(packageName)
            intent.putExtra(MediaStore.EXTRA_OUTPUT, outputFileUri)
            cameraIntents.add(intent)
        }

        //FileSystem
        val galleryIntent = Intent()
        galleryIntent.type = "image/*"
        galleryIntent.action = Intent.ACTION_GET_CONTENT

        // Chooser of filesystem options.
        val chooserIntent = Intent.createChooser(galleryIntent, "Select Source")
        // Add the camera options.
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(arrayOf<Parcelable>()))
        profileFragment.startActivityForResult(chooserIntent, Constants.PICK_IMAGE)
    }


}
package com.elementary.cryptolink.fragments.profile

import android.Manifest
import android.R.attr
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.elementary.cryptolink.R
import com.elementary.cryptolink.databinding.FragmentProfileBinding
import com.elementary.cryptolink.utils.AppUtils
import com.elementary.cryptolink.utils.Constants
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import java.io.*


class ProfileFragment : Fragment() {

    private lateinit var mBinding: FragmentProfileBinding
    private lateinit var mViewModel: ProfileFragmentViewModel
    private lateinit var mContext: Context

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mContext = context!!
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)

        mViewModel = ViewModelProviders.of(this).get(ProfileFragmentViewModel::class.java)
        mViewModel.init(this)
        mBinding.viewModel = mViewModel

        mBinding.executePendingBindings()
        mBinding.lifecycleOwner = this

        return mBinding.root
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        when (requestCode) {
            Constants.PICK_IMAGE -> {
                if (resultCode == Activity.RESULT_OK) {

                    if (requestCode == Constants.PICK_IMAGE) {
                        var isCamera = false

                        isCamera = if (data == null) {
                            true
                        } else {
                            val action = data.action
                            action?.equals(MediaStore.ACTION_IMAGE_CAPTURE) ?: false
                        }

                        val selectedImageUri: Uri
                        if (isCamera) {
                            selectedImageUri = mViewModel.outputFileUri;

                            activity!!.runOnUiThread {
                                val exifInterface = selectedImageUri.path?.let { ExifInterface(it) }
                                val orientation = exifInterface?.getAttributeInt(
                                    ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED
                                )

                                val bitmap: Bitmap = BitmapFactory.decodeFile(selectedImageUri.path)
                                var rotatedBitmap: Bitmap? = null
                                rotatedBitmap = when (orientation) {
                                    ExifInterface.ORIENTATION_ROTATE_90 -> {
                                        rotateImage(bitmap, 90f)
                                    }
                                    ExifInterface.ORIENTATION_ROTATE_180 -> {
                                        rotateImage(bitmap, 180f)
                                    }
                                    ExifInterface.ORIENTATION_ROTATE_270 -> {
                                        rotateImage(bitmap, 270f)
                                    }
                                    else -> {
                                        bitmap
                                    }
                                }

                                val file = File(selectedImageUri.path!!)
                                val os: OutputStream = BufferedOutputStream(FileOutputStream(file))
                                rotatedBitmap?.compress(Bitmap.CompressFormat.JPEG, 100, os)
                                os.close()
                            }
                        } else {
                            selectedImageUri = data?.data!!
                        }

                        if (selectedImageUri != null)
                            CropImage.activity(selectedImageUri)
                                .setFixAspectRatio(true)
                                .setCropShape(CropImageView.CropShape.OVAL)
                                .start(mContext, this);


                        Log.d("Full Path", selectedImageUri.path!!)


                    }

                }
            }
            CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE -> {

                val result = CropImage.getActivityResult(data)
                if (resultCode == RESULT_OK) {
                    val resultUri = result.uri

                    Glide.with(mContext).load(resultUri).into(mBinding.ivProfileImage)

                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    val error = result.error
                }


            }
        }
    }

    fun checkPermissions() {

        if ((ContextCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) ||
            (ContextCompat.checkSelfPermission(mContext, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
            || (ContextCompat.checkSelfPermission(mContext, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)
        ) {
            requestPermissions(
                arrayOf(
                    Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ), Constants.PERMISSION
            )
            return
        } else {
            mViewModel.openImageIntent()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == Constants.PERMISSION) {

            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED
                && grantResults[2] == PackageManager.PERMISSION_GRANTED
            ) {
                mViewModel.openImageIntent()
            } else {
                AppUtils.showToast(activity!!, "Permissions Denied", false)
            }
        }

    }

    private fun rotateImage(source: Bitmap, angle: Float): Bitmap? {
        val matrix = Matrix()
        matrix.postRotate(angle)
        return Bitmap.createBitmap(
            source, 0, 0, source.width, source.height,
            matrix, true
        )
    }


}